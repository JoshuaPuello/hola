package com.serviceplazoleta.domain.api;

import com.serviceplazoleta.domain.model.Order;

import java.util.List;

public interface IPlazoletaServicePort {

    Order processOrder(Order order);

    Boolean clientHasPendingOrder(Long clientId);

    List<Order> getOrdersByStatus(Long employeeId, String status, Integer page, Integer size);
}
