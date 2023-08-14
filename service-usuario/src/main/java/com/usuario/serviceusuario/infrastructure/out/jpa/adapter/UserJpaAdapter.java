package com.usuario.serviceusuario.infrastructure.out.jpa.adapter;

import com.usuario.serviceusuario.domain.model.User;
import com.usuario.serviceusuario.domain.spi.IUserPersistencePort;
import com.usuario.serviceusuario.infrastructure.out.jpa.entity.UserEntity;
import com.usuario.serviceusuario.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.usuario.serviceusuario.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    @Override
    public Optional<UserEntity> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User saveUser(User user) {
        UserEntity userEntity = userRepository.save(userEntityMapper.toEntity(user));
        return userEntityMapper.toUserModel(userEntity);
    }

    @Override
    public User getUserById(Long id) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        UserEntity userEntity = userEntityOptional.orElse(null);
        return userEntityMapper.toUserModel(userEntity);
    }

    @Override
    public User getUserByMail(String mail) {
        Optional<UserEntity> userEntityOptional = userRepository.findByMail(mail);
        UserEntity userEntity = userEntityOptional.orElse(null);
        return userEntityMapper.toUserModel(userEntity);
    }
}
