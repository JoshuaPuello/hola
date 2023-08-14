package com.serviceplazoleta.infrastructure.input.rest;

import com.serviceplazoleta.application.dto.OrderRequestDto;
import com.serviceplazoleta.application.dto.OrderResponseDto;
import com.serviceplazoleta.application.handler.IPlazoletaHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("plazoleta")
public class PlazoletaController {

    private final IPlazoletaHandler plazoletaHandler;

    public PlazoletaController(IPlazoletaHandler plazoletaHandler) {
        this.plazoletaHandler = plazoletaHandler;
    }

    @PostMapping("/processOrder")
    public ResponseEntity<OrderResponseDto> processOrder(
        @Valid @RequestBody OrderRequestDto orderRequestDto
    ) {
        OrderResponseDto order = plazoletaHandler.processOrder(orderRequestDto);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
