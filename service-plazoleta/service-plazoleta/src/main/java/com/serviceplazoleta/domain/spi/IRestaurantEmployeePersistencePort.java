package com.serviceplazoleta.domain.spi;

import com.serviceplazoleta.domain.model.Restaurant;

public interface IRestaurantEmployeePersistencePort {

    Restaurant getRestaurantByEmployeeId(Long employeeId);
}
