package com.usuario.serviceusuario.domain.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
public class User {
    private long id;
    private String name;
    private String lastName;
    private String documentId;
    private String phoneNumber;
    private String mail;
    private String password;
    private Role role;
}
