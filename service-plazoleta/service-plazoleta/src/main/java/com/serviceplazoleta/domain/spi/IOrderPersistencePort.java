package com.serviceplazoleta.domain.spi;

import com.serviceplazoleta.domain.model.Order;

public interface IOrderPersistencePort {

    Order saveOrder(Order order);
}
