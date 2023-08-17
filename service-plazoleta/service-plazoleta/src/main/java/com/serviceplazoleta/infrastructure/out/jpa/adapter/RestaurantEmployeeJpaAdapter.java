package com.serviceplazoleta.infrastructure.out.jpa.adapter;

import com.serviceplazoleta.domain.api.exception.RestaurantNotExistException;
import com.serviceplazoleta.domain.model.Restaurant;
import com.serviceplazoleta.domain.spi.IRestaurantEmployeePersistencePort;
import com.serviceplazoleta.infrastructure.out.jpa.entity.RestaurantEmployeeEntity;
import com.serviceplazoleta.infrastructure.out.jpa.entity.RestaurantEntity;
import com.serviceplazoleta.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.serviceplazoleta.infrastructure.out.jpa.repository.IRestaurantEmployeeRepository;
import com.serviceplazoleta.infrastructure.out.jpa.repository.IRestaurantRepository;

public class RestaurantEmployeeJpaAdapter implements IRestaurantEmployeePersistencePort {

    private final IRestaurantEmployeeRepository restaurantEmployeeRepository;
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;

    public RestaurantEmployeeJpaAdapter(
        IRestaurantEmployeeRepository restaurantEmployeeRepository,
        IRestaurantRepository restaurantRepository,
        IRestaurantEntityMapper restaurantEntityMapper
    ) {
        this.restaurantEmployeeRepository = restaurantEmployeeRepository;
        this.restaurantRepository = restaurantRepository;
        this.restaurantEntityMapper = restaurantEntityMapper;
    }

    @Override
    public Restaurant getRestaurantByEmployeeId(Long employeeId) {
        Long idRestaurant = restaurantEmployeeRepository.findByIdEmployee(employeeId)
                .map(RestaurantEmployeeEntity::getIdRestaurant)
                .orElseThrow(RestaurantNotExistException::new);

        RestaurantEntity restaurantEntity = restaurantRepository.findById(idRestaurant)
                .orElseThrow(RestaurantNotExistException::new);

        return restaurantEntityMapper.toRestaurant(restaurantEntity);
    }
}
