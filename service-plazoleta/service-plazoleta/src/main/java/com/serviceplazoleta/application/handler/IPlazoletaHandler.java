package com.serviceplazoleta.application.handler;

import com.serviceplazoleta.application.dto.OrderRequestDto;
import com.serviceplazoleta.application.dto.OrderResponseDto;
import com.serviceplazoleta.domain.model.Order;

public interface IPlazoletaHandler {

    OrderResponseDto processOrder(OrderRequestDto orderRequestDto);
}
