// Define o pacote onde esse DTO está localizado.
package com.motorplace.back_core.dto;

// Importações das anotações do Lombok para reduzir boilerplate.
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Lombok: Gera automaticamente getters, setters, toString, equals e hashCode.
@Data

// Lombok: Permite criar objetos usando o padrão de projeto Builder (ex: UserResponseDTO.builder().id(1L).build()).
@Builder

// Lombok: Gera um construtor sem argumentos (necessário para frameworks como Jackson ou JPA).
@NoArgsConstructor

// Lombok: Gera um construtor com todos os argumentos (todos os campos como parâmetros).
@AllArgsConstructor
public class UserResponseDTO {

    // Campo que representa o identificador único do usuário.
    private Long id;

    // Campo que representa o nome de usuário.
    private String username;

    // Campo que representa o papel (role) do usuário no sistema, como "ROLE_USER" ou "ROLE_ADMIN".
    private String role;

    // Comentário que indica que você pode adicionar outros campos no futuro,
    // como email, data de criação, status, etc.
    // Exemplo: private String email;
}
