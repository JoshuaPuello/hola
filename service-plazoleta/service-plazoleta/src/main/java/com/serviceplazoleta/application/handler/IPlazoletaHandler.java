package com.serviceplazoleta.application.handler;

import com.serviceplazoleta.application.dto.ProcessOrderRequestDto;
import com.serviceplazoleta.application.dto.OrderResponseDto;

import java.util.List;

public interface IPlazoletaHandler {

    OrderResponseDto processOrder(ProcessOrderRequestDto processOrderRequestDto);

    List<OrderResponseDto> getOrdersByStatus(Long employeeId, Integer page, Integer size, String status);

    OrderResponseDto assignOrderToEmployee(Long employeeId, Long orderId);
}
