package com.serviceplazoleta.domain.api;

import com.serviceplazoleta.domain.model.Dish;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IDishServicePort {

    void saveDish(Dish dish, Long restaurantId, HttpServletRequest request);

    Dish getDishById(Long id);

    void updateDish(Long id, Dish dish, Long restaurantId, HttpServletRequest request, Long propietarioId);

    void enableDisableDish(Long id, Long flag);

    List<Dish> getAllDish();

    List<Dish> findAllByRestaurantId(Long idRestaurant, Integer page, Integer size, String category);

    Boolean validateAccess(Long userId, String requiredRole, String token);
}
