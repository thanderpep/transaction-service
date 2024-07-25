package br.com.caju.infrastructure.repository;

import br.com.caju.infrastructure.entity.MerchantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MerchantRepository extends JpaRepository<MerchantEntity, Long> {
    
    Optional<MerchantEntity> findByName(String name);
}
