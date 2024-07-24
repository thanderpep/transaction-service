package br.com.caju.application.gateway;

import br.com.caju.core.domain.BenefitWallet;
import br.com.caju.core.domain.enums.BenefitCategoryEnum;

import java.util.Optional;

public interface FindBenefitWalletByCategoryAndAccountIdGateway {
    
    Optional<BenefitWallet> findByCategoryAndUserAccountId(BenefitCategoryEnum category, String accountId);
}
