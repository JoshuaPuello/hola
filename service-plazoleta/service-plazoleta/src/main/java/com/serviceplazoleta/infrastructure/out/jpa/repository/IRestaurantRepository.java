package com.serviceplazoleta.infrastructure.out.jpa.repository;


import com.serviceplazoleta.infrastructure.out.jpa.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
    Optional<RestaurantEntity> findByIdProprietor(Long idProprietor);
    Optional<RestaurantEntity> findById(Long id);
}
