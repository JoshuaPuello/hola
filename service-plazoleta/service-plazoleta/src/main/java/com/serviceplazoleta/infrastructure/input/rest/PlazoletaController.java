package com.serviceplazoleta.infrastructure.input.rest;

import com.serviceplazoleta.application.dto.OrderRequestDto;
import com.serviceplazoleta.application.dto.OrderResponseDto;
import com.serviceplazoleta.application.handler.IPlazoletaHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("plazoleta")
public class PlazoletaController {

    private final IPlazoletaHandler plazoletaHandler;

    public PlazoletaController(IPlazoletaHandler plazoletaHandler) {
        this.plazoletaHandler = plazoletaHandler;
    }

    @GetMapping("/getOrdersByStatus/page/{page}/size/{size}")
    public ResponseEntity<List<OrderResponseDto>> getOrdersByStatus(
        @PathVariable(value = "page") Integer page,
        @PathVariable(value = "size") Integer size,
        @RequestParam(value = "status") String status,
        HttpServletRequest request
    ) {
        // Aqui debe validar que el usuario asociado al token sea de rol empleado
        Long employeeId = Long.valueOf((String) request.getAttribute("userId"));
        List<OrderResponseDto> ordersByStatus = plazoletaHandler.getOrdersByStatus(employeeId, page, size, status);
        return new ResponseEntity<>(ordersByStatus, HttpStatus.CREATED);
    }

    @PostMapping("/processOrder")
    public ResponseEntity<OrderResponseDto> processOrder(
        @Valid @RequestBody OrderRequestDto orderRequestDto
    ) {
        OrderResponseDto order = plazoletaHandler.processOrder(orderRequestDto);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
