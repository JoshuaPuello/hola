package com.usuario.serviceusuario.application.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserRequestDto {

    @NotBlank(message = "El nombre es requerido")
    @Size(min = 2, message = "El nombre debe tener al menos 2 caracteres")
    private String name;

    @NotBlank(message = "El apellido es requerido")
    @Size(min = 2, message = "El apellido debe tener al menos 2 caracteres")
    private String lastName;

    @NotBlank(message = "El  documentoDeIdentidad es requerido")
    @Pattern(regexp = "\\d+", message = "El documentoDeIdentidad debe ser númerico")
    @Size(min = 5, message = "El documento de identidad debe tener al menos 5 numeros")
    private String documentId;

    @NotBlank(message = "El celular es requerido")
    @Pattern(regexp = "^\\+?\\d{1,12}$", message = "El número de celular debe contener máximo 13 caracteres y puede contener el símbolo '+' al inicio")
    private String phoneNumber;

    @NotBlank(message = "El correo electrónico es requerido")
    @Email(message = "El correo electrónico debe ser válido")
    private String mail;

    @NotBlank(message = "La clave es requerida")
    private String password;
    private Long role;
}
