package br.com.caju.application.usecaseimpl;

import br.com.caju.application.gateway.FindBenefitWalletByCategoryAndAccountIdGateway;
import br.com.caju.application.gateway.SaveBenefitWalletGateway;
import br.com.caju.core.domain.BenefitWallet;
import br.com.caju.core.domain.User;
import br.com.caju.core.domain.enums.BenefitCategoryEnum;
import br.com.caju.core.exception.BenefitWalletNotFoundException;
import br.com.caju.core.exception.InsufficientBalanceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DecreaseBalanceUseCaseImplTest {

    @InjectMocks
    private DecreaseBalanceUseCaseImpl decreaseBalanceUseCase;

    @Mock
    private FindBenefitWalletByCategoryAndAccountIdGateway findBenefitWalletByCategoryAndAccountIdGateway;
    
    @Mock
    private SaveBenefitWalletGateway saveBenefitWalletGateway;
    
    @Test
    void decreaseBalanceSucess() throws BenefitWalletNotFoundException, InsufficientBalanceException {
            BenefitWallet benefitWallet = mockBenefitWallet();
            String accountId = "123";
            BigDecimal amount = BigDecimal.valueOf(250);
            when(findBenefitWalletByCategoryAndAccountIdGateway.findByCategoryAndUserAccountId(benefitWallet.getCategory(), accountId)).thenReturn(Optional.of(benefitWallet));
        
            decreaseBalanceUseCase.decreaseBalance(accountId, benefitWallet.getCategory(), amount);
        
            verify(saveBenefitWalletGateway).save(benefitWallet);
    }
    
    @Test
    void decreaseBalanceInsufficient() {
        BigDecimal amount = BigDecimal.valueOf(1500);
        when(findBenefitWalletByCategoryAndAccountIdGateway.findByCategoryAndUserAccountId(any(), any())).thenReturn(Optional.of(mockBenefitWallet()));
        
        assertThrows(InsufficientBalanceException.class, () -> decreaseBalanceUseCase.decreaseBalance(any(), any(), amount));
    }
    
    @Test
    void decreaseBalanceBenefitWalletNotFound() {
        BigDecimal amount = BigDecimal.valueOf(250);
        when(findBenefitWalletByCategoryAndAccountIdGateway.findByCategoryAndUserAccountId(any(), any())).thenReturn(Optional.empty());
        
        assertThrows(BenefitWalletNotFoundException.class, () -> decreaseBalanceUseCase.decreaseBalance(any(), any(), amount));
    }
    
    @Test
    void decreaseBalanceInvalidValue() {
        BigDecimal amount = BigDecimal.valueOf(-10);
        when(findBenefitWalletByCategoryAndAccountIdGateway.findByCategoryAndUserAccountId(any(), any())).thenReturn(Optional.of(mockBenefitWallet()));
        
        assertThrows(IllegalArgumentException.class, () -> decreaseBalanceUseCase.decreaseBalance(any(), any(), amount));
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
