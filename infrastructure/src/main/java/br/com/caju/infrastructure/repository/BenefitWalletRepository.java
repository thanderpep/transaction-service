package br.com.caju.infrastructure.repository;

import br.com.caju.core.domain.enums.BenefitCategoryEnum;
import br.com.caju.infrastructure.entity.BenefitWalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BenefitWalletRepository extends JpaRepository<BenefitWalletEntity, Long> {
    
    Optional<BenefitWalletEntity> findByCategoryAndUserAccountId(BenefitCategoryEnum category, String accountId);
}
