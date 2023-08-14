package com.usuario.serviceusuario.infrastructure.input.rest;


import com.usuario.serviceusuario.application.dto.UserRequestDto;
import com.usuario.serviceusuario.application.handler.IUserHandler;
import com.usuario.serviceusuario.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserHandler userHandler;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/propietario")
    public ResponseEntity<User> savePropietario(@RequestBody UserRequestDto request) {
        User user = userHandler.saveUser(request);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/validateRole")
    public ResponseEntity<Boolean> validateRole(@Valid @PathVariable("id") Long userId, @RequestParam("requiredRole") String requiredRole) throws Exception {
        Boolean userHasRole = userHandler.validateUserHasRole(userId, requiredRole);
        return new ResponseEntity<>(userHasRole, HttpStatus.OK);
    }

    @PostMapping("/employee")
    public ResponseEntity<User> saveEmployee(@RequestBody UserRequestDto employee){
        User user = userHandler.saveEmployee(employee);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/client")
    public ResponseEntity<User> saveClient(@Valid @RequestBody UserRequestDto client){
        User user = userHandler.saveUser(client);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
