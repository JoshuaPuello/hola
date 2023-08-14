package com.serviceplazoleta.infrastructure.out.jpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "pedidos_platos")
@NoArgsConstructor
@Getter
@Setter
public class OrderDishEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_order")
    private OrderEntity order;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_dish")
    private DishEntity dish;

    private Long quantity;

    public OrderDishEntity(OrderEntity order, DishEntity dish, Long quantity) {
        this.order = order;
        this.dish = dish;
        this.quantity = quantity;
    }
}
