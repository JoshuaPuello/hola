package com.serviceplazoleta.application.handler;

import com.serviceplazoleta.application.dto.RestaurantPaginationResponseDto;
import com.serviceplazoleta.application.dto.RestaurantRequestDto;
import com.serviceplazoleta.application.dto.RestaurantResponseDto;
import com.serviceplazoleta.application.mapper.IRestaurantPaginationResponseMapper;
import com.serviceplazoleta.application.mapper.IRestaurantRequestMapper;
import com.serviceplazoleta.application.mapper.IRestaurantResponseMapper;
import com.serviceplazoleta.domain.api.IRestaurantServicePort;
import com.serviceplazoleta.domain.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantHandler implements IRestaurantHandler{

    private final IRestaurantServicePort restaurantServicePort;
    private final IRestaurantRequestMapper restaurantRequestMapper;
    private final IRestaurantResponseMapper restaurantResponseMapper;
    private final IRestaurantPaginationResponseMapper restaurantPaginationResponseMapper;

    @Override
    public void saveRestaurant(RestaurantRequestDto restaurantRequestDto, Long propietarioId) throws Exception {
        Restaurant restaurant = restaurantRequestMapper.toRestaurant(restaurantRequestDto);
        restaurantServicePort.saveRestaurant(restaurant, propietarioId);
    }

    @Override
    public RestaurantResponseDto getRestaurantById(Long id) {
        RestaurantResponseDto restaurantResponseDto = restaurantResponseMapper.toResponse(restaurantServicePort.getRestaurantById(id));
        return restaurantResponseDto;
    }

    @Override
    public List<RestaurantResponseDto> getAllRestaurants() {
        return restaurantResponseMapper.toResponseList(restaurantServicePort.getAllRestaurant());
    }

    @Override
    public List<RestaurantPaginationResponseDto> getRestaurantPaginationResponseDto(Integer page, Integer size) {
        return restaurantPaginationResponseMapper.toResponseListPagination(restaurantServicePort.getRestaurantsWhithPagination(page,size));
    }

    @Override
    public void validateAccess(Long userId, String requiredRole, String token) throws Exception {
        Boolean hasValidAccess = restaurantServicePort.validateAccess(userId, requiredRole, token);
        if (!hasValidAccess) {
            throw new Exception("User has no access to this resource.");
        }
    }

    @Override
    public void linkEmployee(Long restaurantId, Long employeeId) {
        restaurantServicePort.linkEmployee(restaurantId, employeeId);
    }

    @Override
    public RestaurantResponseDto getRestaurantByIdPropietario(Long idPropietario) throws Exception {
        Optional<Restaurant> restaurant = restaurantServicePort.getRestaurantByIdPropietario(idPropietario);
        return restaurant.map(restaurantResponseMapper::toResponse).orElse(null);
    }
}
