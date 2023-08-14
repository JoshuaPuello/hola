package com.serviceplazoleta.application.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CategoryRequestDto {

    private Long id;

    @NotBlank(message = "El nombre es requerido")
    private String name;

    @NotBlank(message = "La descripcion es requerida")
    private String description;
}
