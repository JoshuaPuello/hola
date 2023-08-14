package com.usuario.serviceusuario.domain.useCase;

import com.usuario.serviceusuario.application.dto.RestaurantResponseDto;
import com.usuario.serviceusuario.domain.api.IUserServicePort;
import com.usuario.serviceusuario.domain.model.Employee;
import com.usuario.serviceusuario.domain.model.Role;
import com.usuario.serviceusuario.domain.model.User;
import com.usuario.serviceusuario.domain.spi.IUserPersistencePort;
import com.usuario.serviceusuario.domain.spi.feignclients.IEmployeeFeignClientPort;
import com.usuario.serviceusuario.domain.spi.token.IToken;
import com.usuario.serviceusuario.infrastructure.out.feignclients.IRestaurantFeignClient;
import com.usuario.serviceusuario.infrastructure.out.jpa.entity.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;

    private final IRestaurantFeignClient restaurantFeignClient;

    private final PasswordEncoder passwordEncoder;

    private final IToken token;

    public UserUseCase(
        IUserPersistencePort userPersistencePort,
        IRestaurantFeignClient restaurantFeignClient,
        PasswordEncoder passwordEncoder,
        IToken token
    ) {
        this.userPersistencePort = userPersistencePort;
        this.restaurantFeignClient = restaurantFeignClient;
        this.passwordEncoder = passwordEncoder;
        this.token = token;
    }

    @Override
    public User saveUser(User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return userPersistencePort.saveUser(user);
    }

    @Override
    public User getUserById(Long id) {
        return userPersistencePort.getUserById(id);
    }

    @Override
    public User getUserByMail(String mail) {
        return userPersistencePort.getUserByMail(mail);
    }

    @Override
    public Boolean validateUserHasRole(Long userId, String requiredRole) throws Exception {
        UserEntity user = userPersistencePort.findUserById(userId).orElseThrow(() -> new Exception("User not found"));
        return user.getRole().getName().equals(requiredRole);
    }

    @Override
    public void linkEmployeeToRestaurant(User user) {
        String bearerToken = token.getBearerToken();
        Long idOwnerAut = token.getUserAuthenticatedId(bearerToken);

        RestaurantResponseDto restaurant = restaurantFeignClient.getRestaurantByIdPropietario(idOwnerAut);
        restaurantFeignClient.linkEmployeeToRestaurant(restaurant.getId(), user.getId());
    }
}
