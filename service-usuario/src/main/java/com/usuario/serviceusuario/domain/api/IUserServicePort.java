package com.usuario.serviceusuario.domain.api;

import com.usuario.serviceusuario.domain.model.User;

public interface IUserServicePort {

    User saveUser(User user);

    User getUserById(Long id);

    User getUserByMail(String mail);

    Boolean validateUserHasRole(Long userId, String requiredRole) throws Exception;

    void linkEmployeeToRestaurant(User user);
}
