package br.com.caju.application.gateway;

import br.com.caju.core.domain.User;

import java.util.Optional;

public interface FindUserByAccountIdGateway {
    
    Optional<User> findByAccountId(String accountId);
}
