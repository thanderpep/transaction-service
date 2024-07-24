package br.com.caju.usecase;

import br.com.caju.core.domain.enums.BenefitCategoryEnum;

import java.math.BigDecimal;

public interface BalanceSufficientUseCase {
    
    Boolean isBalanceSufficient(String accountId, BenefitCategoryEnum benefitCategory, BigDecimal amount);
}
