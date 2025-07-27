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

    // Forma alternativa (não recomendada quando se usa Spring) de obter uma instância do mapper manualmente.
    // No seu caso, pode ser ignorado, já que o Spring faz a injeção automaticamente.
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // Mapeia os campos de um UserRequestDTO para um User (entidade).
    // Esses mapeamentos são explícitos, mas seriam opcionais se os nomes fossem iguais.
    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "role", source = "role")
    User toEntity(UserRequestDTO dto);

    // Mapeia os campos de um User (entidade) para um UserResponseDTO (retorno para o cliente).
    // O campo "id" é mapeado automaticamente, pois os nomes são iguais e não está omitido aqui.
    @Mapping(target = "username", source = "username")
    @Mapping(target = "role", source = "role")
    UserResponseDTO toDTO(User user);
}
