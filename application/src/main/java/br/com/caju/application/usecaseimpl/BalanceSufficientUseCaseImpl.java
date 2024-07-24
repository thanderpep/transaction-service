package br.com.caju.application.usecaseimpl;

import br.com.caju.application.gateway.FindBenefitWalletByCategoryAndAccountIdGateway;
import br.com.caju.core.domain.BenefitWallet;
import br.com.caju.core.domain.enums.BenefitCategoryEnum;
import br.com.caju.core.exception.BenefitWalletNotFoundException;
import br.com.caju.core.exception.enums.ErrorCodeEnum;
import br.com.caju.usecase.BalanceSufficientUseCase;

import java.math.BigDecimal;

public class BalanceSufficientUseCaseImpl implements BalanceSufficientUseCase {
    
    private FindBenefitWalletByCategoryAndAccountIdGateway findBenefitWalletByCategoryAndAccountIdGateway;
    
    public BalanceSufficientUseCaseImpl(FindBenefitWalletByCategoryAndAccountIdGateway findBenefitWalletByCategoryAndAccountIdGateway) {
        this.findBenefitWalletByCategoryAndAccountIdGateway = findBenefitWalletByCategoryAndAccountIdGateway;
    }
    
    @Override
    public Boolean isBalanceSufficient(String accountId, BenefitCategoryEnum benefitCategory, BigDecimal amount) {
        try {
            BenefitWallet benefitWallet = findBenefitWalletByCategoryAndAccountIdGateway.findByCategoryAndUserAccountId(benefitCategory, accountId)
                    .orElseThrow(() -> new BenefitWalletNotFoundException(ErrorCodeEnum.BW002.getMessage(), ErrorCodeEnum.BW002.getCode()));
        
            return benefitWallet.getBalance().compareTo(amount) >= 0;
        } catch (BenefitWalletNotFoundException e) {
            //TODO log
        }
        return false;
    }
}
