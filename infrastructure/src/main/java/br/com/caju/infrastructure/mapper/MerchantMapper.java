package br.com.caju.infrastructure.mapper;

import br.com.caju.core.domain.Merchant;
import br.com.caju.infrastructure.entity.MerchantEntity;
import org.springframework.stereotype.Component;

@Component
public class MerchantMapper {
    
    public Merchant toMerchant(MerchantEntity entity) {
        return new Merchant(
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                entity.getContactNumber(),
                entity.getEmail(),
                entity.getBusinessType(),
                entity.getMccDefault(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
