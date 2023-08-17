package com.serviceplazoleta.infrastructure.configuration;


import com.serviceplazoleta.application.mapper.IPlazoletaRequestMapper;
import com.serviceplazoleta.application.mapper.IPlazoletaResponseMapper;
import com.serviceplazoleta.domain.api.ICategoryServicePort;
import com.serviceplazoleta.domain.api.IDishServicePort;
import com.serviceplazoleta.domain.api.IPlazoletaServicePort;
import com.serviceplazoleta.domain.api.IRestaurantServicePort;
import com.serviceplazoleta.domain.spi.*;
import com.serviceplazoleta.domain.spi.feignclient.IUserFeignClientPort;
import com.serviceplazoleta.domain.useCase.CategoryUseCase;
import com.serviceplazoleta.domain.useCase.DishUseCase;
import com.serviceplazoleta.domain.useCase.PlazoletaUseCase;
import com.serviceplazoleta.domain.useCase.RestaurantUseCase;
import com.serviceplazoleta.infrastructure.out.feignclient.IUserFeignClient;
import com.serviceplazoleta.infrastructure.out.feignclient.adapter.UserFeignAdapter;
import com.serviceplazoleta.infrastructure.out.feignclient.mapper.IUserDtoMapper;
import com.serviceplazoleta.infrastructure.out.jpa.adapter.*;
import com.serviceplazoleta.infrastructure.out.jpa.mapper.ICategoryEntityMapper;
import com.serviceplazoleta.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.serviceplazoleta.infrastructure.out.jpa.mapper.IOrderEntityMapper;
import com.serviceplazoleta.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.serviceplazoleta.infrastructure.out.jpa.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEmployeeRepository restaurantEmployeeRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;
    private final IOrderEntityMapper orderEntityMapper;
    private final ICategoryRepository categoryRepository;
    private final IOrderRepository orderRepository;
    private final IOrderDishRepository orderDishRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    private final IPlazoletaRequestMapper plazoletaRequestMapper;

    private final IPlazoletaResponseMapper plazoletaResponseMapper;
    private final IUserFeignClient userFeignClient;
    private final IUserDtoMapper userDtoMapper;

    public BeanConfiguration(
        IRestaurantRepository restaurantRepository,
        IRestaurantEmployeeRepository restaurantEmployeeRepository,
        IRestaurantEntityMapper restaurantEntityMapper,
        IDishRepository dishRepository,
        IDishEntityMapper dishEntityMapper,
        IOrderEntityMapper orderEntityMapper,
        ICategoryRepository categoryRepository,
        IOrderRepository orderRepository,
        IOrderDishRepository orderDishRepository,
        ICategoryEntityMapper categoryEntityMapper,
        IPlazoletaRequestMapper plazoletaRequestMapper,
        IPlazoletaResponseMapper plazoletaResponseMapper,
        IUserFeignClient userFeignClient,
        IUserDtoMapper userDtoMapper
    ) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantEmployeeRepository = restaurantEmployeeRepository;
        this.restaurantEntityMapper = restaurantEntityMapper;
        this.dishRepository = dishRepository;
        this.dishEntityMapper = dishEntityMapper;
        this.orderEntityMapper = orderEntityMapper;
        this.categoryRepository = categoryRepository;
        this.orderRepository = orderRepository;
        this.orderDishRepository = orderDishRepository;
        this.categoryEntityMapper = categoryEntityMapper;
        this.plazoletaRequestMapper = plazoletaRequestMapper;
        this.plazoletaResponseMapper = plazoletaResponseMapper;
        this.userFeignClient = userFeignClient;
        this.userDtoMapper = userDtoMapper;
    }

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort() {
        return new RestaurantJpaAdapter(restaurantRepository, restaurantEntityMapper, restaurantEmployeeRepository);
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort() {
        return new RestaurantUseCase(restaurantPersistencePort(), userFeignClientPort());
    }

    @Bean
    public IOrderPersistencePort orderPersistencePort() {
        return new OrderJpaAdapter(orderRepository, orderDishRepository, dishRepository, orderEntityMapper);
    }

    @Bean
    public IRestaurantEmployeePersistencePort restaurantEmployeePersistencePort() {
        return new RestaurantEmployeeJpaAdapter(restaurantEmployeeRepository, restaurantRepository, restaurantEntityMapper);
    }

    @Bean
    public IDishPersistencePort dishPersistencePort() {
        return new DishJpaAdapter(dishRepository, dishEntityMapper);
    }

    @Bean
    public IDishServicePort dishServicePort() {
        return new DishUseCase(dishPersistencePort(), userFeignClientPort(), restaurantPersistencePort());
    }

    @Bean
    public IPlazoletaServicePort plazoletaServicePort() {
        return new PlazoletaUseCase(orderPersistencePort(), restaurantEmployeePersistencePort());
    }

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }

    @Bean
    public IUserFeignClientPort userFeignClientPort() {
        return new UserFeignAdapter(userFeignClient, userDtoMapper);
    }


}
