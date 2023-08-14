package com.serviceplazoleta.domain.useCase;

import com.serviceplazoleta.domain.api.IRestaurantServicePort;
import com.serviceplazoleta.domain.api.exception.OwnerMustOnlyOwnARestaurantException;
import com.serviceplazoleta.domain.model.Restaurant;
import com.serviceplazoleta.domain.spi.IRestaurantPersistencePort;
import com.serviceplazoleta.domain.spi.feignclient.IUserFeignClientPort;

import java.util.List;
import java.util.Optional;

public class RestaurantUseCase implements IRestaurantServicePort {
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IUserFeignClientPort userFeignClientPort;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort, IUserFeignClientPort userFeignClientPort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.userFeignClientPort = userFeignClientPort;
    }

    @Override
    public void saveRestaurant(Restaurant restaurant, Long propietarioId) throws Exception {
        Optional<Restaurant> optionalRestaurant = restaurantPersistencePort.getRestaurantByIdPropietario(propietarioId);
        if (optionalRestaurant.isPresent()) throw new OwnerMustOnlyOwnARestaurantException();
        restaurantPersistencePort.saveRestaurant(restaurant);
    }

    @Override
    public Restaurant getRestaurantById(Long id) {
        return restaurantPersistencePort.getRestaurantById(id);
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantPersistencePort.getAllRestaurant();
    }

    @Override
    public List<Restaurant> getRestaurantsWhithPagination(Integer page, Integer size) {
        return restaurantPersistencePort.getRestaurantsWithPagination(page,size);
    }

    public Boolean validateAccess(Long userId, String requiredRole, String token) {
        return userFeignClientPort.validateUserId(userId, requiredRole, token);
    }

    @Override
    public void linkEmployee(Long restaurantId, Long employeeId) {
        restaurantPersistencePort.linkEmployee(restaurantId, employeeId);
    }

    @Override
    public Optional<Restaurant> getRestaurantByIdPropietario(Long idPropietario) {
        return restaurantPersistencePort.getRestaurantByIdPropietario(idPropietario);
    }
}
