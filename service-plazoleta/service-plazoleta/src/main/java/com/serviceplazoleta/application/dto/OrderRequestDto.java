package com.serviceplazoleta.application.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class OrderRequestDto {

    private Map<Long, Long> dishesIdByQuantity;

    private Long idRestaurant;

    private Long idClient;
}
