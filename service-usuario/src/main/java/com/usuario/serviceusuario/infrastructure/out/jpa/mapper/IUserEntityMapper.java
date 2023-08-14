package com.usuario.serviceusuario.infrastructure.out.jpa.mapper;

import com.usuario.serviceusuario.domain.model.User;
import com.usuario.serviceusuario.infrastructure.out.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserEntityMapper {

    @Mapping(source = "role.id", target = "role.id")
    UserEntity toEntity(User user);

    @Mapping(target = "role.id",source = "role.id")
    User toUserModel(UserEntity userEntity);
}
