package com.serviceplazoleta.infrastructure.out.jpa.repository;

import com.serviceplazoleta.infrastructure.out.jpa.entity.CategoryEntity;
import com.serviceplazoleta.infrastructure.out.jpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {

}
