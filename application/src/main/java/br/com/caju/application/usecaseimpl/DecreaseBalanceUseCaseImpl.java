package br.com.caju.application.usecaseimpl;

import br.com.caju.application.gateway.FindBenefitWalletByCategoryAndAccountIdGateway;
import br.com.caju.application.gateway.SaveBenefitWalletGateway;
import br.com.caju.core.domain.BenefitWallet;
import br.com.caju.core.domain.enums.BenefitCategoryEnum;
import br.com.caju.core.exception.BenefitWalletNotFoundException;
import br.com.caju.core.exception.InsufficientBalanceException;
import br.com.caju.core.exception.enums.ErrorCodeEnum;
import br.com.caju.usecase.DecreaseBalanceUseCase;

import java.math.BigDecimal;

public class DecreaseBalanceUseCaseImpl implements DecreaseBalanceUseCase {
    
    private FindBenefitWalletByCategoryAndAccountIdGateway findBenefitWalletByCategoryAndAccountIdGateway;
    private SaveBenefitWalletGateway saveBenefitWalletGateway;
    
    public DecreaseBalanceUseCaseImpl(FindBenefitWalletByCategoryAndAccountIdGateway findBenefitWalletByCategoryAndAccountIdGateway, SaveBenefitWalletGateway saveBenefitWalletGateway) {
        this.findBenefitWalletByCategoryAndAccountIdGateway = findBenefitWalletByCategoryAndAccountIdGateway;
        this.saveBenefitWalletGateway = saveBenefitWalletGateway;
    }
    
    @Override
    public void decreaseBalance(String accountId, BenefitCategoryEnum benefitCategory, BigDecimal amount) throws BenefitWalletNotFoundException, InsufficientBalanceException {
        BenefitWallet benefitWallet = findBenefitWalletByCategoryAndAccountIdGateway.findByCategoryAndUserAccountId(benefitCategory, accountId)
                .orElseThrow(() -> new BenefitWalletNotFoundException(ErrorCodeEnum.BW002.getMessage(), ErrorCodeEnum.BW002.getCode()));
    
        benefitWallet.debitValue(amount);
    
        saveBenefitWalletGateway.save(benefitWallet);
    }
}
