package br.com.caju.usecase;

import br.com.caju.core.domain.enums.BenefitCategoryEnum;
import br.com.caju.core.exception.BenefitWalletNotFoundException;
import br.com.caju.core.exception.InsufficientBalanceException;

import java.math.BigDecimal;

public interface DecreaseBalanceUseCase {
    
    void decreaseBalance(String accountId, BenefitCategoryEnum benefitCategory, BigDecimal amount) throws BenefitWalletNotFoundException, InsufficientBalanceException;
}
