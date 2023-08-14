package com.usuario.serviceusuario.infrastructure.out.feignclients;

import com.usuario.serviceusuario.application.dto.RestaurantEmployeeResponseDto;
import com.usuario.serviceusuario.application.dto.RestaurantResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(contextId = "restaurant",name = "service-plazoleta", url = "localhost:8091/restaurant")
public interface IRestaurantFeignClient {

    // Este retorna el restaurante basado en el id del propietario
    @GetMapping("/propietario/{id}")
    RestaurantResponseDto getRestaurantByIdPropietario(@PathVariable(value = "id") Long idPropietario);

    @PostMapping("/{id}/linkEmployee")
    RestaurantEmployeeResponseDto linkEmployeeToRestaurant(@PathVariable(value = "id") Long idRestaurant, @RequestParam("employeeId") Long employeeId);
}
