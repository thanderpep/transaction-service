package br.com.caju.infrastructure.service;

import br.com.caju.application.gateway.FindUserByAccountIdGateway;
import br.com.caju.core.domain.User;
import br.com.caju.infrastructure.entity.UserEntity;
import br.com.caju.infrastructure.mapper.UserMapper;
import br.com.caju.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindUserByAccountIdGatewayImpl implements FindUserByAccountIdGateway {
    
    private UserRepository userRepository;
    private UserMapper userMapper;
    
    public FindUserByAccountIdGatewayImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    
    
    @Override
    public Optional<User> findByAccountId(String accountId) {
        Optional<UserEntity> UserEntity = userRepository.findByAccountId(accountId);
        return UserEntity.map(entity -> userMapper.toUser(entity));
    }
}
