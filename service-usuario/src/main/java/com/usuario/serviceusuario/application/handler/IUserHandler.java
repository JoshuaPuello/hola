package com.usuario.serviceusuario.application.handler;

import com.usuario.serviceusuario.application.dto.UserRequestDto;
import com.usuario.serviceusuario.application.dto.UserResponseDto;
import com.usuario.serviceusuario.domain.model.User;

public interface IUserHandler {

    User saveUser(UserRequestDto userRequestDto);

    User saveEmployee(UserRequestDto userRequestDto);

    UserResponseDto getUserById(Long id);

    UserResponseDto getUserByMail(String mail);
    Boolean validateUserHasRole(Long userId, String requiredRole) throws Exception;
}
