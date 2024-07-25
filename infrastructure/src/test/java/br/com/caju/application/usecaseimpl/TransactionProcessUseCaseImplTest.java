package br.com.caju.application.usecaseimpl;


import br.com.caju.application.gateway.SaveTransactionGateway;
import br.com.caju.core.domain.Merchant;
import br.com.caju.core.domain.Transaction;
import br.com.caju.core.domain.User;
import br.com.caju.core.domain.enums.BenefitCategoryEnum;
import br.com.caju.core.domain.enums.ResponseCodeEnum;
import br.com.caju.core.exception.BenefitWalletNotFoundException;
import br.com.caju.core.exception.InsufficientBalanceException;
import br.com.caju.core.exception.MerchantNotFoundException;
import br.com.caju.core.exception.UserNotFoundException;
import br.com.caju.usecase.DecreaseBalanceUseCase;
import br.com.caju.usecase.FindMerchantByNameUseCase;
import br.com.caju.usecase.FindUserByAccountIdUseCase;
import br.com.caju.usecase.ValidateBalanceUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionProcessUseCaseImplTest {
    
    @InjectMocks
    TransactionProcessUseCaseImpl transactionProcessUseCase;
    
    @Mock
    private FindUserByAccountIdUseCase findUserByAccountIdUseCase;
    
    @Mock
    private FindMerchantByNameUseCase findMerchantByNameUseCase;
    
    @Mock
    private ValidateBalanceUseCase validateBalanceUseCase;
    
    @Mock
    private DecreaseBalanceUseCase decreaseBalanceUseCase;
    
    @Mock
    private SaveTransactionGateway saveTransactionGateway;
    
    @Test
    void processTransactionRequestWithoutAccountId() throws UserNotFoundException {
        Transaction transaction = mockTransactionDefault(false, true, true, true);
    
        ResponseCodeEnum responseCodeEnum = transactionProcessUseCase.processTransaction(transaction);
    
        verify(findUserByAccountIdUseCase, never()).findByAccountId(any());
        assertEquals(responseCodeEnum, ResponseCodeEnum.ERROR);
    }
    
    @Test
    void processTransactionRequestWithoutTotalAmout() throws UserNotFoundException {
        Transaction transaction = mockTransactionDefault(true, false, true, true);
        
        ResponseCodeEnum responseCodeEnum = transactionProcessUseCase.processTransaction(transaction);
        
        verify(findUserByAccountIdUseCase, never()).findByAccountId(any());
        assertEquals(responseCodeEnum, ResponseCodeEnum.ERROR);
    }
    
    @Test
    void processTransactionRequestWithoutMcc() throws UserNotFoundException {
        Transaction transaction = mockTransactionDefault(true, true, false, true);
        
        ResponseCodeEnum responseCodeEnum = transactionProcessUseCase.processTransaction(transaction);
        
        verify(findUserByAccountIdUseCase, never()).findByAccountId(any());
        assertEquals(responseCodeEnum, ResponseCodeEnum.ERROR);
    }
    
    @Test
    void processTransactionRequestWithoutMerchant() throws UserNotFoundException {
        Transaction transaction = mockTransactionDefault(true, true, true, false);
        
        ResponseCodeEnum responseCodeEnum = transactionProcessUseCase.processTransaction(transaction);
        
        verify(findUserByAccountIdUseCase, never()).findByAccountId(any());
        assertEquals(responseCodeEnum, ResponseCodeEnum.ERROR);
    }
    
    @Test
    void processTransactionRequestAccountIdNotFound() throws UserNotFoundException {
        Transaction transaction = mockTransactionDefault(true, true, true, true);
        when(findUserByAccountIdUseCase.findByAccountId(any())).thenThrow(UserNotFoundException.class);
        
        ResponseCodeEnum responseCodeEnum = transactionProcessUseCase.processTransaction(transaction);
        
        verify(findUserByAccountIdUseCase).findByAccountId(any());
        assertEquals(responseCodeEnum, ResponseCodeEnum.ERROR);
    }
    
    @Test
    void processTransactionRequestSucessWithBalanceInCategory() throws UserNotFoundException, BenefitWalletNotFoundException, InsufficientBalanceException, MerchantNotFoundException {
        Merchant mockMerchant = mockMerchant(null);
        
        Transaction transaction = mockTransaction(
                "123",
                BigDecimal.TEN,
                BenefitCategoryEnum.FOOD.getMccCodes().get(0),
                mockMerchant.getName()
        );
        
        when(findUserByAccountIdUseCase.findByAccountId(transaction.getAccountId())).thenReturn(mockUser());
        when(findMerchantByNameUseCase.findByName(transaction.getMerchant())).thenReturn(mockMerchant);
        when(validateBalanceUseCase.isBalanceSufficient(any(), any(), any())).thenReturn(true);
    
        ResponseCodeEnum responseCodeEnum = transactionProcessUseCase.processTransaction(transaction);
        
        verify(validateBalanceUseCase, times(1)).isBalanceSufficient(any(), any(), any());
        
        var category = ArgumentCaptor.forClass(BenefitCategoryEnum.class);
        verify(decreaseBalanceUseCase, times(1)).decreaseBalance(any(), category.capture(), any());
    
        assertEquals(category.getValue(), BenefitCategoryEnum.FOOD);
        assertEquals(responseCodeEnum, ResponseCodeEnum.APPROVED);
    }
    
    @Test
    void processTransactionRequestSucessWithMccOfMerchant() throws UserNotFoundException, BenefitWalletNotFoundException, InsufficientBalanceException, MerchantNotFoundException {
        Merchant mockMerchant = mockMerchant("9999");
        
        Transaction transaction = mockTransaction(
                "123",
                BigDecimal.TEN,
                BenefitCategoryEnum.FOOD.getMccCodes().get(0),
                mockMerchant.getName()
        );
        
        when(findUserByAccountIdUseCase.findByAccountId(transaction.getAccountId())).thenReturn(mockUser());
        when(findMerchantByNameUseCase.findByName(transaction.getMerchant())).thenReturn(mockMerchant);
        when(validateBalanceUseCase.isBalanceSufficient(any(), any(), any())).thenReturn(true);
        
        ResponseCodeEnum responseCodeEnum = transactionProcessUseCase.processTransaction(transaction);
        
        verify(validateBalanceUseCase, times(1)).isBalanceSufficient(any(), any(), any());
        
        var category = ArgumentCaptor.forClass(BenefitCategoryEnum.class);
        verify(decreaseBalanceUseCase, times(1)).decreaseBalance(any(), category.capture(), any());
        
        assertEquals(category.getValue(), BenefitCategoryEnum.CASH);
        assertEquals(responseCodeEnum, ResponseCodeEnum.APPROVED);
    }
    
    @Test
    void processTransactionRequestSucessWithoutBalanceInCategory() throws UserNotFoundException, BenefitWalletNotFoundException, InsufficientBalanceException, MerchantNotFoundException {
        Merchant mockMerchant = mockMerchant(null);
        
        Transaction transaction = mockTransaction(
                "123",
                BigDecimal.TEN,
                BenefitCategoryEnum.FOOD.getMccCodes().get(0),
                mockMerchant.getName()
        );
        
        when(findUserByAccountIdUseCase.findByAccountId(transaction.getAccountId())).thenReturn(mockUser());
        when(findMerchantByNameUseCase.findByName(transaction.getMerchant())).thenReturn(mockMerchant);
        when(validateBalanceUseCase.isBalanceSufficient(any(), any(), any()))
                .thenReturn(false)
                .thenReturn(true);
    
        ResponseCodeEnum responseCodeEnum = transactionProcessUseCase.processTransaction(transaction);
        
        verify(validateBalanceUseCase, times(2)).isBalanceSufficient(any(), any(), any());
        
        var category = ArgumentCaptor.forClass(BenefitCategoryEnum.class);
        verify(decreaseBalanceUseCase, times(1)).decreaseBalance(any(), category.capture(), any());
        
        assertEquals(category.getValue(), BenefitCategoryEnum.CASH);
        assertEquals(responseCodeEnum, ResponseCodeEnum.APPROVED);
    }
    
    @Test
    void processTransactionRequestInsufficientBalance() throws UserNotFoundException, BenefitWalletNotFoundException, InsufficientBalanceException, MerchantNotFoundException {
        Merchant mockMerchant = mockMerchant(null);
    
        Transaction transaction = mockTransaction(
                "123",
                BigDecimal.TEN,
                BenefitCategoryEnum.FOOD.getMccCodes().get(0),
                mockMerchant.getName()
        );
        
        when(findUserByAccountIdUseCase.findByAccountId(transaction.getAccountId())).thenReturn(mockUser());
        when(findMerchantByNameUseCase.findByName(transaction.getMerchant())).thenReturn(mockMerchant);
        when(validateBalanceUseCase.isBalanceSufficient(any(), any(), any()))
                .thenReturn(false)
                .thenReturn(false);
        
        ResponseCodeEnum responseCodeEnum = transactionProcessUseCase.processTransaction(transaction);
        
        verify(validateBalanceUseCase, times(2)).isBalanceSufficient(any(), any(), any());
        verify(decreaseBalanceUseCase, times(0)).decreaseBalance(any(), any(), any());
        
        assertEquals(responseCodeEnum, ResponseCodeEnum.INSUFFICIENT_BALANCE);
    }
    
    @Test
    void processTransactionRequestGenericError() throws UserNotFoundException, BenefitWalletNotFoundException, InsufficientBalanceException, MerchantNotFoundException {
        Merchant mockMerchant = mockMerchant(null);
        
        Transaction transaction = mockTransaction(
                "123",
                BigDecimal.TEN,
                BenefitCategoryEnum.FOOD.getMccCodes().get(0),
                mockMerchant.getName()
        );
        
        when(findUserByAccountIdUseCase.findByAccountId(transaction.getAccountId())).thenThrow(UserNotFoundException.class);
        
        ResponseCodeEnum responseCodeEnum = transactionProcessUseCase.processTransaction(transaction);
        
        assertEquals(responseCodeEnum, ResponseCodeEnum.ERROR);
    }
    
    private Transaction mockTransactionDefault(boolean isAccount, boolean isTotalAmount, boolean isMcc, boolean isMerchant) {
        return new Transaction(
                isAccount ? "123" : null,
                isTotalAmount ? BigDecimal.valueOf(100.00) : null,
                isMcc ? "5811" : null,
                isMerchant ? "PADARIA DO ZE               SAO PAULO BR" : null
        );
    }
    
    private Transaction mockTransaction(String accountId, BigDecimal totalAmount, String mcc, String merchant) {
        return new Transaction(accountId, totalAmount, mcc, merchant);
    }
    
    private Merchant mockMerchant(String mcc) {
        Merchant merchant = new Merchant();
        merchant.setId(1L);
        merchant.setName("UBER TRIP                   SAO PAULO BR");
        merchant.setMccDefault(mcc);
        return merchant;
    }
    
    private User mockUser() {
        User user = new User();
        user.setId(1L);
        user.setAccountId("123");
        return user;
    }
}
