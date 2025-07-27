package com.motorplace.back_core.mapper;

import com.motorplace.back_core.dto.UserRequestDTO;
import com.motorplace.back_core.dto.UserResponseDTO;
import com.motorplace.back_core.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "role", source = "role")
    User toEntity(UserRequestDTO dto);

    @Mapping(target = "username", source = "username")
    @Mapping(target = "role", source = "role")
    UserResponseDTO toDTO(User user);
}
