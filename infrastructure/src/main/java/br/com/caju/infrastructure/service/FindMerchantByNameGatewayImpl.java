package br.com.caju.infrastructure.service;

import br.com.caju.application.gateway.FindMerchantByNameGateway;
import br.com.caju.core.domain.Merchant;
import br.com.caju.infrastructure.entity.MerchantEntity;
import br.com.caju.infrastructure.mapper.MerchantMapper;
import br.com.caju.infrastructure.repository.MerchantRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindMerchantByNameGatewayImpl implements FindMerchantByNameGateway {
    
    private MerchantRepository merchantRepository;
    private MerchantMapper merchantMapper;
    
    public FindMerchantByNameGatewayImpl(MerchantRepository merchantRepository, MerchantMapper merchantMapper) {
        this.merchantRepository = merchantRepository;
        this.merchantMapper = merchantMapper;
    }
    
    @Override
    public Optional<Merchant> findByName(String name) {
        Optional<MerchantEntity> merchantEntity = merchantRepository.findByName(name);
        return merchantEntity.map(entity -> merchantMapper.toMerchant(entity));
    }
}
