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

@Service
@RequiredArgsConstructor
@Transactional
public class PlazoletaHandler implements IPlazoletaHandler {

    private final IPlazoletaServicePort plazoletaServicePort;
    private final IPlazoletaRequestMapper plazoletaRequestMapper;
    private final IPlazoletaResponseMapper plazoletaResponseMapper;

    @Override
    public OrderResponseDto processOrder(OrderRequestDto orderRequestDto) {
        Order order = plazoletaRequestMapper.toOrder(orderRequestDto);
        return plazoletaResponseMapper.toResponse(plazoletaServicePort.processOrder(order));
    }
}
