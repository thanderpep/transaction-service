package br.com.caju.application.gateway;

import br.com.caju.core.domain.BenefitWallet;

public interface SaveBenefitWalletGateway {
    
    BenefitWallet save(BenefitWallet benefitWallet);
}
