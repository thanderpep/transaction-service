package br.com.caju.application.usecaseimpl;


import br.com.caju.core.domain.User;
import br.com.caju.core.domain.Merchant;
import br.com.caju.core.domain.Transaction;
import br.com.caju.core.domain.enums.BenefitCategoryEnum;
import br.com.caju.core.domain.enums.ResponseCodeEnum;
import br.com.caju.core.exception.InsufficientBalanceException;
import br.com.caju.core.exception.MerchantNotFoundException;
import br.com.caju.core.exception.TransactionException;
import br.com.caju.core.exception.enums.ErrorCodeEnum;
import br.com.caju.usecase.*;
import br.com.caju.application.gateway.SaveTransactionGateway;

import static java.util.Objects.isNull;

public class TransactionProcessUseCaseImpl implements TransactionProcessUseCase {
    
    private FindUserByAccountIdUseCase findUserByAccountIdUseCase;
    private FindMerchantByNameUseCase findMerchantByNameUseCase;
    private ValidateBalanceUseCase validateBalanceUseCase;
    private DecreaseBalanceUseCase decreaseBalanceUseCase;
    private SaveTransactionGateway saveTransactionGateway;
    
    public TransactionProcessUseCaseImpl(FindUserByAccountIdUseCase findUserByAccountIdUseCase, FindMerchantByNameUseCase findMerchantByNameUseCase, ValidateBalanceUseCase validateBalanceUseCase, DecreaseBalanceUseCase decreaseBalanceUseCase, SaveTransactionGateway saveTransactionGateway) {
        this.findUserByAccountIdUseCase = findUserByAccountIdUseCase;
        this.findMerchantByNameUseCase = findMerchantByNameUseCase;
        this.validateBalanceUseCase = validateBalanceUseCase;
        this.decreaseBalanceUseCase = decreaseBalanceUseCase;
        this.saveTransactionGateway = saveTransactionGateway;
    }
    
    @Override
    public ResponseCodeEnum processTransaction(Transaction transaction) {
        try {
            transaction.validate();
            
            User user = findUserByAccountIdUseCase.findByAccountId(transaction.getAccountId());
        
            // L3: Verifica se o comerciante tem um MCC preferencial, se positivo,
            // substitui o MCC da transação pois o comerciante tem maior precedência
            updateMccInTrasactionByMerchantName(transaction);
        
            // L1: Mapeia o MCC para o tipo de saldo
            BenefitCategoryEnum benefitCategory = BenefitCategoryEnum.fromMcc(transaction.getMcc());
        
            boolean balanceSufficient = false;
        
            // L2: Fallback para CASH
            if (!benefitCategory.equals(BenefitCategoryEnum.CASH)) {
                balanceSufficient = validateBalanceUseCase.isBalanceSufficient(user.getAccountId(), benefitCategory, transaction.getAmount());
            
                if (!balanceSufficient)
                    benefitCategory = BenefitCategoryEnum.CASH;
            }
        
            if (benefitCategory.equals(BenefitCategoryEnum.CASH)) {
                balanceSufficient = validateBalanceUseCase.isBalanceSufficient(user.getAccountId(), benefitCategory, transaction.getAmount());
            }
        
            // Tenta processar a transação com o tipo de saldo mapeado
            if (balanceSufficient) {
                decreaseBalanceUseCase.decreaseBalance(user.getAccountId(), benefitCategory, transaction.getAmount());
                saveTransactionGateway.save(transaction);
                return ResponseCodeEnum.APPROVED;
            } else
                throw new InsufficientBalanceException(ErrorCodeEnum.BW001.getMessage(), ErrorCodeEnum.BW001.getCode());
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
            // Se o saldo não for suficiente, rejeita a transação
            return ResponseCodeEnum.INSUFFICIENT_BALANCE;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // Qualquer outro problema que impeça a transação de ser processada
            return ResponseCodeEnum.ERROR;
        }
    }
    
    private void updateMccInTrasactionByMerchantName(Transaction transaction) throws TransactionException {
        try {
            Merchant merchant = findMerchantByNameUseCase.findByName(transaction.getMerchant());
            
            if (!isNull(merchant.getMccDefault()) && !merchant.getMccDefault().isBlank())
                transaction.setMcc(merchant.getMccDefault());
        } catch (MerchantNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
