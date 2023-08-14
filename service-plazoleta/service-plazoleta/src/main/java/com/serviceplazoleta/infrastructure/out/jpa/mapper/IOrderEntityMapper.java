package com.serviceplazoleta.infrastructure.out.jpa.mapper;

import com.serviceplazoleta.domain.model.Order;
import com.serviceplazoleta.infrastructure.out.jpa.entity.OrderDishEntity;
import com.serviceplazoleta.infrastructure.out.jpa.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IOrderEntityMapper {

    OrderEntity toEntity(Order order);

    @Mapping(target = "dishesByQuantity", expression = "java(mapDishesIdByQuantity(orderDishEntities))")
    Order toOrder(OrderEntity orderEntity, List<OrderDishEntity> orderDishEntities);

    default Map<String, Long> mapDishesIdByQuantity(List<OrderDishEntity> orderDishEntities) {
        Map<String, Long> map = new HashMap<>();
        for (OrderDishEntity orderDishEntity : orderDishEntities) {
            if (map.put(orderDishEntity.getDish().getName(), orderDishEntity.getQuantity()) != null) {
                throw new IllegalStateException("Duplicate key");
            }
        }
        return map;
    }
}
