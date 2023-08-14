package com.serviceplazoleta.infrastructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "restaurant_employee")
@NoArgsConstructor
@Getter
@Setter
public class RestaurantEmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idRestaurant;
    private Long idEmployee;

    public RestaurantEmployeeEntity(Long idRestaurant, Long idEmployee) {
        this.idRestaurant = idRestaurant;
        this.idEmployee = idEmployee;
    }
}
