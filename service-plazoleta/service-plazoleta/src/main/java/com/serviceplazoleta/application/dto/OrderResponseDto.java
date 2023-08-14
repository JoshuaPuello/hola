package com.serviceplazoleta.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class OrderResponseDto {

    private Long id;

    private Map<String, Long> dishesByQuantity;

    private Long idRestaurant;

    private Long idClient;
}
