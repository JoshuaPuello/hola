package com.usuario.serviceusuario.infrastructure.out.feignclients.adapter;

import com.usuario.serviceusuario.application.dto.RestaurantResponseDto;
import com.usuario.serviceusuario.application.mapper.IRestaurantResponseMapper;
import com.usuario.serviceusuario.domain.model.Restaurant;
import com.usuario.serviceusuario.domain.spi.feignclients.IRestaurantFeingClientPort;
import com.usuario.serviceusuario.infrastructure.out.feignclients.IRestaurantFeignClient;

public class RestaurantFeignAdapter implements IRestaurantFeingClientPort {
    private final IRestaurantFeignClient restaurantFeignClient;
    private final IRestaurantResponseMapper restaurantResponseMapper;

    public RestaurantFeignAdapter(IRestaurantFeignClient restaurantFeignClient, IRestaurantResponseMapper restaurantResponseMapper) {
        this.restaurantFeignClient = restaurantFeignClient;
        this.restaurantResponseMapper = restaurantResponseMapper;
    }

    @Override
    public Restaurant getRestaurantByIdProprietor(Long idProprietor) {
        RestaurantResponseDto restaurantResponseDto = restaurantFeignClient.getRestaurantByIdPropietario(idProprietor);
        return restaurantResponseMapper.toRestaurant(restaurantResponseDto);
    }
}
