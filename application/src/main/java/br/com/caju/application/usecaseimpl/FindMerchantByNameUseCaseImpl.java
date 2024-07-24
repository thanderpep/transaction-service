package br.com.caju.application.usecaseimpl;


import br.com.caju.core.domain.Merchant;
import br.com.caju.core.exception.MerchantNotFoundException;
import br.com.caju.core.exception.enums.ErrorCodeEnum;
import br.com.caju.usecase.FindMerchantByNameUseCase;
import br.com.caju.application.gateway.FindMerchantByNameGateway;

public class FindMerchantByNameUseCaseImpl implements FindMerchantByNameUseCase {
    
    private FindMerchantByNameGateway findMerchantByNameGateway;
    
    public FindMerchantByNameUseCaseImpl(FindMerchantByNameGateway findMerchantByNameGateway) {
        this.findMerchantByNameGateway = findMerchantByNameGateway;
    }
    
    @Override
    public Merchant findByName(String name) throws MerchantNotFoundException {
        return findMerchantByNameGateway.findByName(name)
                .orElseThrow(() -> new MerchantNotFoundException(ErrorCodeEnum.ME001.getMessage(), ErrorCodeEnum.ME001.getCode()));
    }
}
