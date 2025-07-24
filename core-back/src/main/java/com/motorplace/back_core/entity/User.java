package com.motorplace.back_core.entity;

// Importações do Jakarta Persistence (JPA) para mapeamento da entidade no banco de dados
import jakarta.persistence.*;
// Anotações do Lombok para gerar código automaticamente
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A classe User representa a entidade de usuário no sistema.
 * Essa entidade está associada à tabela "users" no banco de dados.
 *
 * Lombok:
 * - @Data: Gera automaticamente getters, setters, toString(), equals() e hashCode().
 * - @NoArgsConstructor: Cria um construtor sem argumentos.
 * - @AllArgsConstructor: Cria um construtor com todos os atributos da classe.
 *
 * JPA:
 * - @Entity: Indica que a classe é uma entidade JPA.
 * - @Table(name = "users"): Mapeia a entidade para a tabela "users".
 */

 @Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;

    private String username;
    private String password;
    private String role;
}
