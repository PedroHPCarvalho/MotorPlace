package com.motorplace.back_core.mapper;

// Importações do MapStruct para mapeamento entre entidades e DTOs
import com.motorplace.back_core.dto.UserRequestDTO;
import com.motorplace.back_core.dto.UserResponseDTO;
import com.motorplace.back_core.entity.User;
import org.mapstruct.Mapper;


// @Mapper é uma anotação do MapStruct que indica que esta interface é um mapeador

//(componentModel = "spring") significa que o MapStruct irá gerar uma implementação dessa interface como um
// componente Spring, permitindo que seja injetada em outros componentes do Spring, como serviços ou controladores.
@Mapper(componentModel = "spring")
public interface UserMapper {
    //Faz a conversão de UserRequestDTO para User
    UserRequestDTO toDTO (User user);
    //Faz a conversão de User para UserResponseDTO
    User toEntity (UserResponseDTO dto);
}
