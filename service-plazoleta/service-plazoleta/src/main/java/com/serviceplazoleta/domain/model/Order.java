package com.serviceplazoleta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Long id;

    private Map<Long, Long> dishesIdByQuantity;

    private Map<String, Long> dishesByQuantity;

    private Long idRestaurant;

    private Long idClient;
}
