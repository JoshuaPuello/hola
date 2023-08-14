package com.serviceplazoleta.application.handler;

import com.serviceplazoleta.application.dto.DishRequestDto;
import com.serviceplazoleta.application.dto.DishResponseDto;
import com.serviceplazoleta.application.dto.DishUpdateRequestDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IDishHandler {

    void saveDish(DishRequestDto dishRequestDto, Long restaurantId, HttpServletRequest request);

    DishResponseDto getDishById(Long id, DishUpdateRequestDto dishUpdateRequestDto);

    void updateDish(Long id, DishUpdateRequestDto dishUpdateRequestDto, Long restaurantId, HttpServletRequest request, Long propietarioId);

    List<DishResponseDto> getAllDishes();

    List<DishResponseDto> findAllByRestaurantId(Long idRestaurant, Integer page, Integer size, String category);

    void enableDisableDish(Long id, Long flag);

    void validateAccess(Long userId, String requiredRole, String token) throws Exception;
}
