package com.usuario.serviceusuario.infrastructure.configuration;


import com.usuario.serviceusuario.application.mapper.IEmployeeRequestMapper;
import com.usuario.serviceusuario.application.mapper.IRestaurantResponseMapper;
import com.usuario.serviceusuario.domain.api.IRoleServicePort;
import com.usuario.serviceusuario.domain.api.IUserServicePort;
import com.usuario.serviceusuario.domain.spi.IRolePersistencePort;
import com.usuario.serviceusuario.domain.spi.IUserPersistencePort;
import com.usuario.serviceusuario.domain.spi.feignclients.IEmployeeFeignClientPort;
import com.usuario.serviceusuario.domain.spi.feignclients.IRestaurantFeingClientPort;
import com.usuario.serviceusuario.domain.spi.token.IToken;
import com.usuario.serviceusuario.domain.useCase.RoleUseCase;
import com.usuario.serviceusuario.domain.useCase.UserUseCase;
import com.usuario.serviceusuario.infrastructure.out.feignclients.IEmployeeFeignClient;
import com.usuario.serviceusuario.infrastructure.out.feignclients.IRestaurantFeignClient;
import com.usuario.serviceusuario.infrastructure.out.feignclients.adapter.EmployeeFeignAdapter;
import com.usuario.serviceusuario.infrastructure.out.feignclients.adapter.RestaurantFeignAdapter;
import com.usuario.serviceusuario.infrastructure.out.jpa.adapter.RoleJpaAdapter;
import com.usuario.serviceusuario.infrastructure.out.jpa.adapter.UserJpaAdapter;
import com.usuario.serviceusuario.infrastructure.out.jpa.mapper.IRoleEntityMapper;
import com.usuario.serviceusuario.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.usuario.serviceusuario.infrastructure.out.jpa.repository.IRoleRepository;
import com.usuario.serviceusuario.infrastructure.out.jpa.repository.IUserRepository;
import com.usuario.serviceusuario.infrastructure.out.tokenAdapter.TokenAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfiguration {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;
    private final PasswordEncoder passwordEncoder;

    private final IRestaurantFeignClient restaurantFeignClient;
    private final IEmployeeFeignClient employeeFeignClient;
    private final IEmployeeRequestMapper employeeRequestMapper;

    private final IRestaurantResponseMapper restaurantResponseMapper;

    public BeanConfiguration(IUserRepository userRepository, IUserEntityMapper userEntityMapper, IRoleRepository roleRepository, IRoleEntityMapper roleEntityMapper, PasswordEncoder passwordEncoder, IRestaurantFeignClient restaurantFeignClient, IEmployeeFeignClient employeeFeignClient, IEmployeeRequestMapper employeeRequestMapper, IRestaurantResponseMapper restaurantResponseMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
        this.roleRepository = roleRepository;
        this.roleEntityMapper = roleEntityMapper;
        this.passwordEncoder = passwordEncoder;
        this.restaurantFeignClient = restaurantFeignClient;
        this.employeeFeignClient = employeeFeignClient;
        this.employeeRequestMapper = employeeRequestMapper;
        this.restaurantResponseMapper = restaurantResponseMapper;
    }



    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserJpaAdapter(userRepository, userEntityMapper);
    }
    @Bean
    public IUserServicePort userServicePort(){
        return new UserUseCase(userPersistencePort(), restaurantFeignClient, passwordEncoder, token());
    }

    @Bean
    public IRolePersistencePort rolePersistencePort(){
        return new RoleJpaAdapter(roleRepository,roleEntityMapper);
    }

    @Bean
    public IRoleServicePort roleServicePort(){
        return new RoleUseCase(rolePersistencePort());
    }

    @Bean
    public IToken token(){
        return new TokenAdapter();
    }

    @Bean
    public IRestaurantFeingClientPort restaurantFeingClientPort(){
        return new RestaurantFeignAdapter(restaurantFeignClient,restaurantResponseMapper);
    }

    @Bean
    public IEmployeeFeignClientPort employeeFeignClientPort(){
        return new EmployeeFeignAdapter(employeeFeignClient, employeeRequestMapper);
    }
}
