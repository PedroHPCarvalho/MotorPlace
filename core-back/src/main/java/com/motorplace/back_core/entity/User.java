package com.motorplace.back_core.entity;

// Importações para JPA (persistência) e Lombok
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * Representa um usuário no sistema, sendo mapeado para a tabela "users" no banco de dados.
 *
 * Anotações Lombok:
 * - @Data: Gera automaticamente os métodos getters, setters, toString(), equals() e hashCode().
 * - @NoArgsConstructor: Gera um construtor padrão (sem parâmetros).
 * - @AllArgsConstructor: Gera um construtor com todos os campos.
 * - @Builder(toBuilder = true): Permite criar objetos usando o padrão Builder e
 *   também gerar uma cópia modificável com .toBuilder().
 *
 * Anotações JPA:
 * - @Entity: Marca esta classe como uma entidade JPA.
 * - @Table(name = "users"): Define o nome da tabela no banco de dados como "users".
 */
@Entity
@Table(name = "users")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true) // Habilita uso de .toBuilder()
public class User {

    /**
     * Identificador único do usuário.
     * @Id indica que é a chave primária.
     * @GeneratedValue(strategy = GenerationType.IDENTITY) delega ao banco a geração incremental de ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;

    /**
     * Nome de usuário usado para login/autenticação.
     */
    @NotBlank(message = "Username is required")
    @Size(min = 10 ,max = 200, message = "Username must be between 10 and 200 characters")
    private String username;

    /**
     * Senha (recomenda-se aplicar criptografia/hashing).
     */
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    private String password;

    /**
     * Papel (role) do usuário no sistema. Ex: "ADMIN", "USER", etc.
     */
    @Builder.Default
    private String role = "ROLE_USER"; // Default role is USER
}
