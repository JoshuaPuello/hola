package com.serviceplazoleta.infrastructure.out.jpa.repository;


import com.serviceplazoleta.infrastructure.out.jpa.entity.RestaurantEmployeeEntity;
import com.serviceplazoleta.infrastructure.out.jpa.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRestaurantEmployeeRepository extends JpaRepository<RestaurantEmployeeEntity, Long> { }
