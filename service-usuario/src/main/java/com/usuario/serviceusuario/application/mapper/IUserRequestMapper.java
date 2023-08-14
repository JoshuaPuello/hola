package com.usuario.serviceusuario.application.mapper;


import com.usuario.serviceusuario.application.dto.UserRequestDto;
import com.usuario.serviceusuario.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.control.MappingControl;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserRequestMapper {

    @Mapping(target = "role.id", source = "role")
    User toUser(UserRequestDto userRequestDto);
}
