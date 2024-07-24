package br.com.caju.infrastructure.repository;

import br.com.caju.infrastructure.entity.MerchantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MerchantRepository extends JpaRepository<MerchantEntity, Long> {
    
    Optional<MerchantEntity> findByName(String name);
}
