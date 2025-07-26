package com.motorplace.back_core.repository;
// Importação da classe User do packege Entity
import com.motorplace.back_core.dto.UserResponseDTO;
import com.motorplace.back_core.entity.User;
// Importação da interface JpaRepository, interface que já fornece codigos pré-prontos para interagir com banco de dados
import org.springframework.data.jpa.repository.JpaRepository;

public interface  UserRepository extends JpaRepository<User, Long> {
     UserResponseDTO findByUsernameRepo (String username);
}
