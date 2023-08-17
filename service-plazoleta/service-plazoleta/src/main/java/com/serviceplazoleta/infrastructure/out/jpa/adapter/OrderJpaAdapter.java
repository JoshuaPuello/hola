package com.serviceplazoleta.infrastructure.out.jpa.adapter;

import com.serviceplazoleta.domain.model.Order;
import com.serviceplazoleta.domain.model.enums.OrderStatus;
import com.serviceplazoleta.domain.spi.IOrderPersistencePort;
import com.serviceplazoleta.infrastructure.out.jpa.entity.DishEntity;
import com.serviceplazoleta.infrastructure.out.jpa.entity.OrderDishEntity;
import com.serviceplazoleta.infrastructure.out.jpa.entity.OrderEntity;
import com.serviceplazoleta.infrastructure.out.jpa.mapper.IOrderEntityMapper;
import com.serviceplazoleta.infrastructure.out.jpa.repository.IDishRepository;
import com.serviceplazoleta.infrastructure.out.jpa.repository.IOrderDishRepository;
import com.serviceplazoleta.infrastructure.out.jpa.repository.IOrderRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;
import java.util.stream.Collectors;

public class OrderJpaAdapter implements IOrderPersistencePort {

    private final IOrderRepository orderRepository;
    private final IOrderDishRepository orderDishRepository;
    private final IDishRepository dishRepository;
    private final IOrderEntityMapper orderEntityMapper;

    public OrderJpaAdapter(
        IOrderRepository orderRepository,
        IOrderDishRepository orderDishRepository,
        IDishRepository dishRepository,
        IOrderEntityMapper orderEntityMapper
    ) {
        this.orderRepository = orderRepository;
        this.orderDishRepository = orderDishRepository;
        this.dishRepository = dishRepository;
        this.orderEntityMapper = orderEntityMapper;
    }
    @Override
    public Order saveOrder(Order order) {
        OrderEntity entity = orderEntityMapper.toEntity(order);
        entity.setDateCreated(new Date());
        entity.setStatus(OrderStatus.PENDIENTE.getStatus());
        OrderEntity orderEntity = orderRepository.save(entity);

        List<OrderDishEntity> orderDishEntities = order.getDishesIdByQuantity().entrySet().stream()
            .map(entry -> {
                Optional<DishEntity> dish = dishRepository.findById(entry.getKey());
                return dish.map(dishEntity -> new OrderDishEntity(orderEntity, dishEntity, entry.getValue())).orElse(null);
            })
            .filter(obj -> !Objects.isNull(obj))
            .collect(Collectors.toList());

        orderDishRepository.saveAll(orderDishEntities);

        return orderEntityMapper.toOrder(orderEntity, orderDishEntities);
    }

    @Override
    public List<Order> findByRestaurantIdAndStatus(Long restaurantId, String status, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        List<OrderEntity> orderEntities = orderRepository.findByIdRestaurantAndStatus(restaurantId, status, pageable);
        return orderEntities.stream()
                .map(orderEntity -> {
                    List<OrderDishEntity> orderDishEntities = orderDishRepository.findByOrderId(orderEntity.getId());
                    return orderEntityMapper.toOrder(orderEntity, orderDishEntities);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Boolean hasOrderWithStatusForClientId(String status, Long clientId) {
        return orderRepository.findByStatusAndIdClient(status, clientId)
                .stream()
                .anyMatch(order -> order.getStatus().equalsIgnoreCase(OrderStatus.PENDIENTE.getStatus()));
    }
}
