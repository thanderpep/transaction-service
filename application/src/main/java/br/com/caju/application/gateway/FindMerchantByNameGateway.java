package br.com.caju.application.gateway;


import br.com.caju.core.domain.Merchant;

import java.util.Optional;

public interface FindMerchantByNameGateway {
    
    Optional<Merchant> findByName(String name);
}
