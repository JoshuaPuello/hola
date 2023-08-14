package com.usuario.serviceusuario.infrastructure.security;

import com.usuario.serviceusuario.domain.model.User;
import com.usuario.serviceusuario.infrastructure.out.jpa.entity.UserEntity;
import com.usuario.serviceusuario.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.usuario.serviceusuario.infrastructure.out.jpa.repository.IUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    public UserDetailsServiceImpl(IUserRepository userRepository, IUserEntityMapper userEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByMail(mail)
                .orElseThrow(()-> new UsernameNotFoundException("Usuario no existe"));
        User user = userEntityMapper.toUserModel(userEntity);

        return new UserDetailsImpl(user);
    }
}
