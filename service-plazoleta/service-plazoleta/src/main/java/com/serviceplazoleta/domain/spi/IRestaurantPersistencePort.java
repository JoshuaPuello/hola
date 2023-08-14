package com.serviceplazoleta.domain.spi;

import com.serviceplazoleta.domain.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface IRestaurantPersistencePort {

    Restaurant saveRestaurant(Restaurant restaurant);
    Restaurant getRestaurantById(Long id);
    Restaurant getIdProprietor(Long idProprietor);
    Optional<Restaurant> getRestaurantByIdPropietario(Long idProprietor);

    List<Restaurant> getAllRestaurant();

    List<Restaurant> getRestaurantsWithPagination(Integer page, Integer size);

    void linkEmployee(Long restaurantId, Long employeeId);
}
