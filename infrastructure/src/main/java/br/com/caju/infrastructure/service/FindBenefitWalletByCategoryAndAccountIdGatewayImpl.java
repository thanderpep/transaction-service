package br.com.caju.infrastructure.service;

import br.com.caju.application.gateway.FindBenefitWalletByCategoryAndAccountIdGateway;
import br.com.caju.core.domain.BenefitWallet;
import br.com.caju.core.domain.enums.BenefitCategoryEnum;
import br.com.caju.infrastructure.entity.BenefitWalletEntity;
import br.com.caju.infrastructure.mapper.BenefitWalletMapper;
import br.com.caju.infrastructure.repository.BenefitWalletRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindBenefitWalletByCategoryAndAccountIdGatewayImpl implements FindBenefitWalletByCategoryAndAccountIdGateway {
    
    private BenefitWalletRepository benefitWalletRepository;
    private BenefitWalletMapper benefitWalletMapper;
    
    public FindBenefitWalletByCategoryAndAccountIdGatewayImpl(BenefitWalletRepository benefitWalletRepository, BenefitWalletMapper benefitWalletMapper) {
        this.benefitWalletRepository = benefitWalletRepository;
        this.benefitWalletMapper = benefitWalletMapper;
    }
    
    public Optional<BenefitWallet> findByCategoryAndUserAccountId(BenefitCategoryEnum category, String accountId) {
        Optional<BenefitWalletEntity> benefitWalletEntity = benefitWalletRepository.findByCategoryAndUserAccountId(category, accountId);
        return benefitWalletEntity.map(entity -> benefitWalletMapper.toBenefitWallet(entity));
    }
}
