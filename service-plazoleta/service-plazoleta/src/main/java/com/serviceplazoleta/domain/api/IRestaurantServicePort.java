package com.serviceplazoleta.domain.api;

import com.serviceplazoleta.domain.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface IRestaurantServicePort {

    void saveRestaurant(Restaurant restaurantModel, Long propietarioId) throws Exception;

    Restaurant getRestaurantById(Long id);

    List<Restaurant> getAllRestaurant();

    List<Restaurant> getRestaurantsWhithPagination(Integer page, Integer siza);

    Boolean validateAccess(Long userId, String requiredRole, String token);

    void linkEmployee(Long restaurantId, Long employeeId);

    Optional<Restaurant> getRestaurantByIdPropietario(Long idPropietario) throws Exception;
}
