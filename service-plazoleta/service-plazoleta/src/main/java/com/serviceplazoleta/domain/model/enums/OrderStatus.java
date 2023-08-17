package com.serviceplazoleta.domain.model.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    PENDIENTE("pendiente");

    private final String status;
    
    OrderStatus(String status) {
        this.status = status;
    }
}
