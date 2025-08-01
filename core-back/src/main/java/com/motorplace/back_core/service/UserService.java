package com.motorplace.back_core.service;

import com.motorplace.back_core.dto.UserRequestDTO;
import com.motorplace.back_core.dto.UserResponseDTO;
import com.motorplace.back_core.entity.User;
import com.motorplace.back_core.mapper.UserMapper;
import com.motorplace.back_core.repository.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
// @Validated ativa o suporte a validações do Bean Validation nas entradas dos métodos do serviço
@Validated
public class UserService {

    // Injeção das dependências necessárias: repositório e mapper
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    // Construtor do serviço com injeção de dependência explícita
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    /**
     * Cria um novo usuário a partir de um DTO (validação incluída).
     * O método espera um corpo de requisição com os dados do usuário.
     */
    public UserResponseDTO createUserServ(@Valid @RequestBody UserRequestDTO dto) {
        // Converte o DTO em entidade
        User user = userMapper.toEntity(dto);

        // Salva no banco de dados
        User savedUser = userRepository.save(user);

        // Converte a entidade salva de volta para DTO de resposta
        return userMapper.toDTO(savedUser);
    }

    /**
     * Lista todos os usuários cadastrados no sistema.
     */
    public List<UserResponseDTO> listUsers() {
        // Busca todos os usuários e converte cada um para DTO usando stream
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca um usuário pelo nome de usuário (username).
     * Retorna o DTO do usuário, ou null se não encontrado.
     */
    public UserResponseDTO findByUsername(@NotBlank String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        // Se presente, retorna o DTO; caso contrário, retorna null
        // Alternativas mais robustas: lançar exceção ou retornar Optional<UserResponseDTO>
        return userOptional.map(userMapper::toDTO).orElse(null);
    }

    /**
     * Busca um usuário pelo ID.
     * Lança exceção com status 404 caso o usuário não seja encontrado.
     */
    public UserResponseDTO findById(@NotBlank Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuário com ID " + id + " não encontrado"
                ));
    }

    /**
     * Atualiza os dados de um usuário existente.
     * Se o usuário não for encontrado, lança exceção genérica.
     */
    public UserResponseDTO updateUser(Long id, @Valid UserRequestDTO user) {
        // Verifica se o usuário existe
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Cria uma nova instância do usuário com os dados atualizados, mantendo o mesmo ID
        User updatedUserInst = existingUser.toBuilder()
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .build();

        // Salva a atualização e retorna o DTO
        return userMapper.toDTO(userRepository.save(updatedUserInst));
    }

    /**
     * Remove um usuário pelo ID.
     * Se o ID não existir, lança exceção.
     */
    public void deleteUser(Long id) {
        // Verifica se o ID existe antes de deletar
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado");
        }

        // Realiza a exclusão
        userRepository.deleteById(id);
    }
}
