package com.serviceplazoleta.infrastructure.out.jpa.mapper;

import com.serviceplazoleta.domain.model.Restaurant;
import com.serviceplazoleta.infrastructure.out.jpa.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IRestaurantEntityMapper {
    RestaurantEntity toEntity(Restaurant restaurant);
    Restaurant toRestaurant(RestaurantEntity restaurantEntity);

    List<Restaurant> toRestaurantList(List<RestaurantEntity> restaurantList);


}
