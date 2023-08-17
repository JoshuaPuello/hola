package com.serviceplazoleta.application.handler;

import com.serviceplazoleta.application.dto.OrderRequestDto;
import com.serviceplazoleta.application.dto.OrderResponseDto;

import java.util.List;

public interface IPlazoletaHandler {

    OrderResponseDto processOrder(OrderRequestDto orderRequestDto);

    List<OrderResponseDto> getOrdersByStatus(Long employeeId, Integer page, Integer size, String status);
}
