package br.com.caju.infrastructure.mapper;

import br.com.caju.core.domain.User;
import br.com.caju.infrastructure.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    
    public User toUser(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getAccountId(),
                entity.getFullName(),
                entity.getAddress(),
                entity.getContactNumber(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
    
    public UserEntity toUserEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getAccountId(),
                user.getFullName(),
                user.getAddress(),
                user.getContactNumber(),
                user.getEmail(),
                user.getPassword(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
