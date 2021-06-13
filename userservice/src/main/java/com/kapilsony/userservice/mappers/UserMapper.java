package com.kapilsony.userservice.mappers;

import com.kapilsony.userservice.dto.UserRequest;
import com.kapilsony.userservice.dto.UserResponse;
import com.kapilsony.userservice.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Mapping(target = "id", source = "userId")
    public abstract UserEntity toEntity(UserRequest userRequest);

    @Mapping(target = "userId",source = "id")
    public abstract UserResponse toDTO(UserEntity userEntity);

//    @Mapping(target = "id", source = "userId")
    @Mapping(target = "id", ignore = true)
    public abstract UserEntity updateUserFromDTO(@MappingTarget UserEntity userEntity, UserRequest userRequest);
}
