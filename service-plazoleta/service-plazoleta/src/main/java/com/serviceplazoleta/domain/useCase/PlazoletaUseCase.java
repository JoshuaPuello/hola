package com.serviceplazoleta.domain.useCase;

import com.serviceplazoleta.domain.api.IPlazoletaServicePort;
import com.serviceplazoleta.domain.model.Order;
import com.serviceplazoleta.domain.spi.IOrderPersistencePort;

public class PlazoletaUseCase implements IPlazoletaServicePort {

    private final IOrderPersistencePort orderPersistencePort;

    public PlazoletaUseCase(IOrderPersistencePort orderPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
    }

    @Override
    public Order processOrder(Order order) {
        return orderPersistencePort.saveOrder(order);
    }
}
