package com.serviceplazoleta.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ProcessOrderRequestDto {

    private Map<Long, Long> dishesIdByQuantity;

    private Long idRestaurant;

    private Long idClient;
}
