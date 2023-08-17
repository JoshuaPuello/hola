package com.serviceplazoleta.domain.useCase;

import com.serviceplazoleta.domain.api.IPlazoletaServicePort;
import com.serviceplazoleta.domain.model.Order;
import com.serviceplazoleta.domain.model.Restaurant;
import com.serviceplazoleta.domain.model.enums.OrderStatus;
import com.serviceplazoleta.domain.spi.IOrderPersistencePort;
import com.serviceplazoleta.domain.spi.IRestaurantEmployeePersistencePort;

import java.util.List;

public class PlazoletaUseCase implements IPlazoletaServicePort {

    private final IOrderPersistencePort orderPersistencePort;
    private final IRestaurantEmployeePersistencePort restaurantEmployeePersistencePort;

    public PlazoletaUseCase(
        IOrderPersistencePort orderPersistencePort,
        IRestaurantEmployeePersistencePort restaurantEmployeePersistencePort
    ) {
        this.orderPersistencePort = orderPersistencePort;
        this.restaurantEmployeePersistencePort = restaurantEmployeePersistencePort;
    }

    @Override
    public Order processOrder(Order order) {
        return orderPersistencePort.saveOrder(order);
    }

    @Override
    public Boolean clientHasPendingOrder(Long clientId) {
        return orderPersistencePort.hasOrderWithStatusForClientId(OrderStatus.PENDIENTE.getStatus(), clientId);
    }

    @Override
    public List<Order> getOrdersByStatus(Long employeeId, String status, Integer page, Integer size) {
        Restaurant restaurant = restaurantEmployeePersistencePort.getRestaurantByEmployeeId(employeeId);
        return orderPersistencePort.findByRestaurantIdAndStatus(restaurant.getId(), status, page, size);
    }
}
