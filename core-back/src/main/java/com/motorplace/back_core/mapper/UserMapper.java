// Define o pacote onde está localizado o mapper.
package com.motorplace.back_core.mapper;

// Importa os DTOs que serão usados na conversão.
import com.motorplace.back_core.dto.UserRequestDTO;
import com.motorplace.back_core.dto.UserResponseDTO;

// Importa a entidade User, que representa a estrutura no banco de dados.
import com.motorplace.back_core.entity.User;

// Importa as anotações do MapStruct.
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


// Indica que essa interface é um mapper do MapStruct.
// "componentModel = 'spring'" permite que o Spring injete esse mapper automaticamente como um bean.
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "role", source = "role")
    User toEntity(UserRequestDTO dto);

    @Mapping(target = "username", source = "username")
    @Mapping(target = "role", source = "role")
    UserResponseDTO toDTO(User user);
}
