package br.com.caju.application.usecaseimpl;


import br.com.caju.application.gateway.FindMerchantByNameGateway;
import br.com.caju.core.domain.Merchant;
import br.com.caju.core.exception.MerchantNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class FindMerchantByNameUseCaseImplTest {
    
    @InjectMocks
    private FindMerchantByNameUseCaseImpl findMerchantByNameUseCase;
    
    @Mock
    private FindMerchantByNameGateway findMerchantByNameGateway;
    
    @Test
    void findByNameSucess() throws MerchantNotFoundException {
        String name = "UBER TRIP                   SAO PAULO BR";
        when(findMerchantByNameGateway.findByName(name)).thenReturn(Optional.of(mockMerchant()));
    
        Merchant merchant = findMerchantByNameUseCase.findByName(name);
        
        assertEquals(merchant.getName(), name);
    }
    
    @Test
    void findByNameNotFound() {
        when(findMerchantByNameGateway.findByName(any())).thenReturn(Optional.empty());
    
        assertThrows(MerchantNotFoundException.class, () -> findMerchantByNameUseCase.findByName(any()));
    }
    
    private Merchant mockMerchant() {
        Merchant merchant = new Merchant();
        merchant.setId(1L);
        merchant.setName("UBER TRIP                   SAO PAULO BR");
        return merchant;
    }
}
