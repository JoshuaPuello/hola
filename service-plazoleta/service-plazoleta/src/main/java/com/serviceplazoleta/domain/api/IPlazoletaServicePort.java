package com.serviceplazoleta.domain.api;

import com.serviceplazoleta.domain.model.Order;

public interface IPlazoletaServicePort {

    Order processOrder(Order order);
}
