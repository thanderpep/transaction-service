package br.com.caju.application.usecaseimpl;

import br.com.caju.application.gateway.FindUserByAccountIdGateway;
import br.com.caju.core.domain.User;
import br.com.caju.core.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindUserByAccountIdUseCaseImplTest {
    
    @InjectMocks
    private FindUserByAccountIdUseCaseImpl findUserByAccountIdUseCase;
    
    @Mock
    private FindUserByAccountIdGateway findUserByAccountIdGateway;
    
    @Test
    void findByAccountIdSucess() throws UserNotFoundException {
        String accountId = "123";
        when(findUserByAccountIdGateway.findByAccountId(accountId)).thenReturn(Optional.of(mockUser()));
    
        User user = findUserByAccountIdUseCase.findByAccountId(accountId);
        
        assertEquals(user.getAccountId(), accountId);
    }
    
    @Test
    void findByAccountIdNotFound() {
        when(findUserByAccountIdGateway.findByAccountId(any())).thenReturn(Optional.empty());
        
        assertThrows(UserNotFoundException.class, () -> findUserByAccountIdUseCase.findByAccountId(any()));
    }
    
    private User mockUser() {
        User user = new User();
        user.setId(1L);
        user.setAccountId("123");
        return user;
    }
}
