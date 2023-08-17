package com.serviceplazoleta.application.handler;

import com.serviceplazoleta.application.dto.ProcessOrderRequestDto;
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
    public OrderResponseDto processOrder(ProcessOrderRequestDto processOrderRequestDto) {
        Boolean hasPendingOrder = plazoletaServicePort.clientHasPendingOrder(processOrderRequestDto.getIdClient());
        if (hasPendingOrder) {
            // throw exception here and remove return null
            return null;
        }
        Order order = plazoletaRequestMapper.toOrder(processOrderRequestDto);
        return plazoletaResponseMapper.toResponse(plazoletaServicePort.processOrder(order));
    }

    @Override
    public List<OrderResponseDto> getOrdersByStatus(Long employeeId, Integer page, Integer size, String status) {
        return plazoletaServicePort.getOrdersByStatus(employeeId, status, page, size)
                .stream()
                .map(iPlazoletaResponseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponseDto assignOrderToEmployee(Long employeeId, Long orderId) {
        return null;
    }
}
