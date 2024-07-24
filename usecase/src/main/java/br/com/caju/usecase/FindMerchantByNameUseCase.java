package br.com.caju.usecase;


import br.com.caju.core.domain.Merchant;
import br.com.caju.core.exception.MerchantNotFoundException;

public interface FindMerchantByNameUseCase {
    
    Merchant findByName(String name) throws MerchantNotFoundException;
}
