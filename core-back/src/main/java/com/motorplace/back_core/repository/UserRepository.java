// Define o pacote onde está localizado o repositório.
package com.motorplace.back_core.repository;

// Importa a entidade User, que será usada como modelo de dados para o repositório.
import com.motorplace.back_core.entity.User;

// Importa a interface JpaRepository, que fornece os métodos prontos para acesso ao banco de dados.
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Interface que define o repositório de acesso à entidade User.
// Estende JpaRepository, que já oferece métodos como save(), findById(), findAll(), delete() etc.
public interface UserRepository extends JpaRepository<User, Long> {

     // Define um método de busca personalizado por username.
     // O Spring Data JPA automaticamente entende que isso é uma query: SELECT * FROM users WHERE username = ?
     Optional<User> findByUsername(String username);
}
