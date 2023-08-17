package com.serviceplazoleta.application.handler;

import com.serviceplazoleta.application.dto.OrderRequestDto;
import com.serviceplazoleta.application.dto.OrderResponseDto;
import com.serviceplazoleta.application.mapper.IPlazoletaRequestMapper;
import com.serviceplazoleta.application.mapper.IPlazoletaResponseMapper;
import com.serviceplazoleta.domain.api.IPlazoletaServicePort;
import com.serviceplazoleta.domain.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PlazoletaHandler implements IPlazoletaHandler {

    private final IPlazoletaServicePort plazoletaServicePort;
    private final IPlazoletaRequestMapper plazoletaRequestMapper;
    private final IPlazoletaResponseMapper plazoletaResponseMapper;
    private final IPlazoletaResponseMapper iPlazoletaResponseMapper;

    @Override
    public OrderResponseDto processOrder(OrderRequestDto orderRequestDto) {
        Order order = plazoletaRequestMapper.toOrder(orderRequestDto);
        return plazoletaResponseMapper.toResponse(plazoletaServicePort.processOrder(order));
    }

    @Override
    public List<OrderResponseDto> getOrdersByStatus(Long employeeId, Integer page, Integer size, String status) {
        return plazoletaServicePort.getOrdersByStatus(employeeId, status, page, size)
                .stream()
                .map(iPlazoletaResponseMapper::toResponse)
                .collect(Collectors.toList());
    }
}
