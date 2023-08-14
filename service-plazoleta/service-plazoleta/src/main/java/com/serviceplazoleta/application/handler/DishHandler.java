package com.serviceplazoleta.application.handler;

import com.serviceplazoleta.application.dto.DishRequestDto;
import com.serviceplazoleta.application.dto.DishResponseDto;
import com.serviceplazoleta.application.dto.DishUpdateRequestDto;
import com.serviceplazoleta.application.mapper.IDishRequestMapper;
import com.serviceplazoleta.application.mapper.IDishResponseMapper;
import com.serviceplazoleta.domain.api.IDishServicePort;
import com.serviceplazoleta.domain.model.Dish;
import com.serviceplazoleta.infrastructure.exception.NoDataFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DishHandler implements IDishHandler {

    private final IDishServicePort dishServicePort;
    private final IDishRequestMapper dishRequestMapper;
    private final IDishResponseMapper dishResponseMapper;

    @Override
    public void saveDish(DishRequestDto dishRequestDto, Long restaurantId, HttpServletRequest request) {
        Dish dish = dishRequestMapper.toDish(dishRequestDto);
        dishServicePort.saveDish(dish, restaurantId, request);
    }

    @Override
    public DishResponseDto getDishById(Long id, DishUpdateRequestDto dishUpdateRequestDto) {
        Dish dish = dishServicePort.getDishById(id);
        return dishResponseMapper.toResponse(dish);
    }

    @Override
    public void updateDish(Long id, DishUpdateRequestDto dishUpdateRequestDto, Long restaurantId, HttpServletRequest request, Long propietarioId) {
        Dish dish = dishRequestMapper.toDishUpdate(dishUpdateRequestDto);
        dishServicePort.updateDish(id, dish, restaurantId, request, propietarioId);
    }

    @Override
    public List<DishResponseDto> getAllDishes() {
        List<Dish> dishList = dishServicePort.getAllDish();
        if (dishList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return dishResponseMapper.toResponseList(dishList);
    }

    @Override
    public List<DishResponseDto> findAllByRestaurantId(
        Long idRestaurant,
        Integer page,
        Integer size,
        String category
    ) {
        List<Dish> dishList = dishServicePort.findAllByRestaurantId(idRestaurant, page, size, category);
        if (dishList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return dishResponseMapper.toResponseList(dishList);
    }

    @Override
    public void enableDisableDish(Long id, Long flag) {
        dishServicePort.enableDisableDish(id, flag);
    }


    @Override
    public void validateAccess(Long userId, String requiredRole, String token) throws Exception {
        Boolean hasValidAccess = dishServicePort.validateAccess(userId, requiredRole, token);
        if (!hasValidAccess) {
            throw new Exception("User has no access to this resource.");
        }
    }
}
