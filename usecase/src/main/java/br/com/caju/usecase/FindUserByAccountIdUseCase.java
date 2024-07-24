package br.com.caju.usecase;

import br.com.caju.core.domain.User;
import br.com.caju.core.exception.UserNotFoundException;

public interface FindUserByAccountIdUseCase {
    
    User findByAccountId(String accountId) throws UserNotFoundException;
}
