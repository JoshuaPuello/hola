package com.usuario.serviceusuario.application.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestaurantResponseDto {

    private Long id;
    private String name;
    private String nit;
    private String address;
    private String phone;
    private String logoUrl;
    private Long idProprietor;
}
