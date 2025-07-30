// Define o pacote onde este DTO está localizado.
package com.motorplace.back_core.dto;

// Importações de anotações para validações dos campos.
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// Importações do Lombok, que ajudam a reduzir o boilerplate.
import lombok.*;

// Lombok: Gera automaticamente os métodos getters, setters, equals, hashCode e toString.
@Data

// Lombok: Permite utilizar o padrão de construção "Builder" (objeto imutável e encadeamento de métodos).
@Builder

// Lombok: Gera um construtor sem argumentos.
@NoArgsConstructor

// Lombok: Gera um construtor com todos os argumentos.
@AllArgsConstructor
public class UserRequestDTO {

    // Validação: o campo username não pode ser nulo ou vazio.
    @NotBlank(message = "Username is required")

    // Validação: define que o username deve ter entre 10 e 200 caracteres.
    @Size(min = 10, max = 200, message = "Username must be between 10 and 200 characters")
    private String username;

    // Validação: o campo password também não pode ser nulo ou vazio.
    @NotBlank(message = "Password is required")

    // Validação: define que a senha deve ter entre 8 e 100 caracteres.
    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    private String password;

    // Campo opcional com valor padrão definido como "ROLE_USER".
    // O Lombok @Builder.Default garante que esse valor seja usado no padrão Builder se nenhum outro valor for passado.
    @Builder.Default
    private String role = "ROLE_USER";
}
