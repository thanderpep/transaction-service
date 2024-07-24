package br.com.caju.infrastructure.service;

import br.com.caju.application.gateway.SaveBenefitWalletGateway;
import br.com.caju.core.domain.BenefitWallet;
import br.com.caju.infrastructure.entity.BenefitWalletEntity;
import br.com.caju.infrastructure.mapper.BenefitWalletMapper;
import br.com.caju.infrastructure.repository.BenefitWalletRepository;
import org.springframework.stereotype.Service;

@Service
public class SaveBenefitWalletGatewayImpl implements SaveBenefitWalletGateway {
    
    private BenefitWalletRepository benefitWalletRepository;
    private BenefitWalletMapper benefitWalletMapper;
    
    public SaveBenefitWalletGatewayImpl(BenefitWalletRepository benefitWalletRepository, BenefitWalletMapper benefitWalletMapper) {
        this.benefitWalletRepository = benefitWalletRepository;
        this.benefitWalletMapper = benefitWalletMapper;
    }
    
    @Override
    public BenefitWallet save(BenefitWallet benefitWallet) {
        BenefitWalletEntity benefitWalletEntity = benefitWalletMapper.toBenefitWalletEntity(benefitWallet);
        benefitWalletEntity = benefitWalletRepository.save(benefitWalletEntity);
        return benefitWalletMapper.toBenefitWallet(benefitWalletEntity);
    }
}
