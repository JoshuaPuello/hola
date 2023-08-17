package com.serviceplazoleta.domain.useCase;

import com.serviceplazoleta.domain.api.IDishServicePort;
import com.serviceplazoleta.domain.api.exception.DishNotExistException;
import com.serviceplazoleta.domain.api.exception.RestaurantNotExistException;
import com.serviceplazoleta.domain.model.Dish;
import com.serviceplazoleta.domain.spi.IDishPersistencePort;
import com.serviceplazoleta.domain.spi.IRestaurantPersistencePort;
import com.serviceplazoleta.domain.spi.feignclient.IUserFeignClientPort;
import com.serviceplazoleta.infrastructure.exception.DifferentOwnerException;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
public class DishUseCase implements IDishServicePort {
    private final IDishPersistencePort dishPersistencePort;
    private final IUserFeignClientPort userFeignClientPort;
    private final IRestaurantPersistencePort restaurantPersistencePort;

    @Override
    public void saveDish(Dish dish, Long restaurantId, HttpServletRequest request) {
        Dish dish1 = dishPersistencePort.getDishByIdRestaurant(restaurantId);
        if (dish1 != null) throw new RestaurantNotExistException();
        Long userId = Long.valueOf((String) request.getAttribute("userId"));
        Long idProprietorRestaurant = restaurantPersistencePort.getRestaurantById(dish.getRestaurant().getId()).getIdProprietor();

        if (!userId.equals(idProprietorRestaurant)) throw new DifferentOwnerException();
        dish.setActive(true);
        dishPersistencePort.saveDish(dish);
    }

    @Override
    public Dish getDishById(Long id) {
        return dishPersistencePort.getDishById(id);
    }

    @Override
    public void updateDish(Long id, Dish dish, Long restaurantId, HttpServletRequest request, Long propietarioId) {
        Dish dish1 = dishPersistencePort.getDishById(id);
        if (dish1 == null) throw new DishNotExistException();

        Dish dish2 = dishPersistencePort.getDishByIdRestaurant(restaurantId);
        if (dish2 != null) throw new RestaurantNotExistException();

        Long userId = Long.valueOf((String) request.getAttribute("userId"));
        Long idProprietorRestaurant = dish1.getRestaurant().getIdProprietor();

        if (userId != idProprietorRestaurant) throw new DifferentOwnerException();
        dish1.setPrice(dish.getPrice());
        dish1.setDescription(dish.getDescription());
        dishPersistencePort.saveDish(dish1);
    }

    @Override
    public void enableDisableDish(Long id, Long flag) {
        Dish dish = dishPersistencePort.getDishById(id);
        if (dish == null) throw new DishNotExistException();

        boolean isEnableOrDisable = (flag == 1) ? true : false;

        dish.setActive(isEnableOrDisable);
        dishPersistencePort.saveDish(dish);

    }

    @Override
    public List<Dish> getAllDish() {
        return dishPersistencePort.getAllDish();
    }

    @Override
    public List<Dish> findAllByRestaurantId(Long idRestaurant, Integer page, Integer size, String category) {
        return dishPersistencePort.findAllByRestaurantId(idRestaurant, page, size, category);
    }

    @Override
    public Boolean validateAccess(Long userId, String requiredRole, String token) {
        return userFeignClientPort.validateUserId(userId, requiredRole, token);
    }
}
