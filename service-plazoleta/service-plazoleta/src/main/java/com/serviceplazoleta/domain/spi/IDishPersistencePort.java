package com.serviceplazoleta.domain.spi;

import com.serviceplazoleta.domain.model.Dish;

import java.util.List;

public interface IDishPersistencePort {

    Dish saveDish(Dish dish);

    Dish getDishById(Long id);

    Dish getDishByIdRestaurant(Long RestaurantId);

    List<Dish> getAllDish();

    List<Dish> findAllByRestaurantId(Long idRestaurant, Integer page, Integer size, String category);
}
