package com.serviceplazoleta.domain.spi;

import com.serviceplazoleta.domain.model.Order;

import java.util.List;

public interface IOrderPersistencePort {

    Order saveOrder(Order order);

    List<Order> findByRestaurantIdAndStatus(Long restaurantId, String status, Integer page, Integer size);

    Boolean hasOrderWithStatusForClientId(String status, Long clientId);
}
