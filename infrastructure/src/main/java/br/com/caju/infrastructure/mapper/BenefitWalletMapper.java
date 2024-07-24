package br.com.caju.infrastructure.mapper;

import br.com.caju.core.domain.BenefitWallet;
import br.com.caju.infrastructure.entity.BenefitWalletEntity;
import org.springframework.stereotype.Component;

@Component
public class BenefitWalletMapper {
    
    private UserMapper userMapper;
    
    public BenefitWalletMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    
    public BenefitWallet toBenefitWallet(BenefitWalletEntity entity) {
        return new BenefitWallet(
                entity.getId(),
                entity.getName(),
                entity.getBalance(),
                entity.getCategory(),
                userMapper.toUser(entity.getUser()),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
    
    public BenefitWalletEntity toBenefitWalletEntity(BenefitWallet benefitWallet) {
        return new BenefitWalletEntity(
                benefitWallet.getId(),
                benefitWallet.getName(),
                benefitWallet.getBalance(),
                benefitWallet.getCategory(),
                userMapper.toUserEntity(benefitWallet.getAccount()),
                benefitWallet.getCreatedAt(),
                benefitWallet.getUpdatedAt()
        );
    }
}
