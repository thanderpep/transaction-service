package br.com.caju.application.usecaseimpl;

import br.com.caju.application.gateway.FindBenefitWalletByCategoryAndAccountIdGateway;
import br.com.caju.core.domain.BenefitWallet;
import br.com.caju.core.domain.User;
import br.com.caju.core.domain.enums.BenefitCategoryEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidateBalanceUseCaseImplTest {
    
    @InjectMocks
    private ValidateBalanceUseCaseImpl validateBalanceUseCase;
    
    @Mock
    private FindBenefitWalletByCategoryAndAccountIdGateway findBenefitWalletByCategoryAndAccountIdGateway;
    
    @Test
    void isBalanceSufficientTrue() {
        BigDecimal amount = BigDecimal.valueOf(250);
        when(findBenefitWalletByCategoryAndAccountIdGateway.findByCategoryAndUserAccountId(any(), any())).thenReturn(Optional.of(mockBenefitWallet()));
    
        Boolean balanceSufficient = validateBalanceUseCase.isBalanceSufficient(any(), any(), amount);
        
        assertTrue(balanceSufficient);
    }
    
    @Test
    void isBalanceSufficientFalse() {
        BigDecimal amount = BigDecimal.valueOf(1250);
        when(findBenefitWalletByCategoryAndAccountIdGateway.findByCategoryAndUserAccountId(any(), any())).thenReturn(Optional.of(mockBenefitWallet()));
        
        Boolean balanceSufficient = validateBalanceUseCase.isBalanceSufficient(any(), any(), amount);
        
        assertFalse(balanceSufficient);
    }
    
    private BenefitWallet mockBenefitWallet() {
        BenefitWallet benefitWallet = new BenefitWallet();
        benefitWallet.setId(1L);
        benefitWallet.setBalance(BigDecimal.valueOf(1000L));
        benefitWallet.setCategory(BenefitCategoryEnum.MEAL);
        benefitWallet.setUser(mockUser());
        return benefitWallet;
    }
    
    private User mockUser() {
        User user = new User();
        user.setId(1L);
        user.setAccountId("123");
        return user;
    }
}
