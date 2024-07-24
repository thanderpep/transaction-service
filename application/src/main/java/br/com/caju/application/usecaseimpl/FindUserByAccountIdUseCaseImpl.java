package br.com.caju.application.usecaseimpl;

import br.com.caju.application.gateway.FindUserByAccountIdGateway;
import br.com.caju.core.domain.User;
import br.com.caju.core.exception.UserNotFoundException;
import br.com.caju.core.exception.enums.ErrorCodeEnum;
import br.com.caju.usecase.FindUserByAccountIdUseCase;

public class FindUserByAccountIdUseCaseImpl implements FindUserByAccountIdUseCase {
    
    private FindUserByAccountIdGateway findUserByAccountIdGateway;
    
    public FindUserByAccountIdUseCaseImpl(FindUserByAccountIdGateway findUserByAccountIdGateway) {
        this.findUserByAccountIdGateway = findUserByAccountIdGateway;
    }
    
    @Override
    public User findByAccountId(String accountId) throws UserNotFoundException {
        return findUserByAccountIdGateway.findByAccountId(accountId)
                .orElseThrow(() -> new UserNotFoundException(ErrorCodeEnum.AC001.getMessage(), ErrorCodeEnum.AC001.getCode()));
    }
}
