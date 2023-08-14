package com.serviceplazoleta.application.mapper;

import com.serviceplazoleta.application.dto.OrderRequestDto;
import com.serviceplazoleta.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlazoletaRequestMapper {

    Order toOrder(OrderRequestDto orderRequestDto);
}
