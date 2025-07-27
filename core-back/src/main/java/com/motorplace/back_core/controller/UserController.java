package com.motorplace.back_core.controller;

import com.motorplace.back_core.dto.UserRequestDTO;
import com.motorplace.back_core.dto.UserResponseDTO;
import com.motorplace.back_core.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    // Injeta uma instância da classe de serviço UserService
    private final UserService userService;

    // Construtor que injeta a dependência do UserService
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Cria um logger para registrar informações no console/log
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    // CREATE - Endpoint POST para criar um novo usuário
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        // Loga o username e a senha recebidos no corpo da requisição
        log.info("Username: {}", userRequestDTO.getUsername());
        log.info("Password: {}", userRequestDTO.getPassword());

        // Chama o serviço para criar o usuário e retorna o DTO de resposta
        UserResponseDTO createdUser = userService.createUserServ(userRequestDTO);

        // Retorna status 201 (CREATED) com o usuário criado
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    // READ - Endpoint GET para buscar um usuário pelo ID
    @GetMapping("/{idSearch}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable("idSearch") Long idSearch) {
        // Chama o serviço para buscar o usuário por ID
        UserResponseDTO findUser = userService.findById(idSearch);

        // Retorna o usuário com status 200 (OK)
        return ResponseEntity.ok(findUser);
    }

    // READ - Endpoint GET para buscar um usuário pelo username usando query param (?username=...)
    @GetMapping("/search")
    public ResponseEntity<UserResponseDTO> getUserByUsername (@RequestParam String username){
        // Chama o serviço para buscar o usuário por username
        UserResponseDTO findUser = userService.findByUsername(username);

        // Retorna o usuário encontrado com status 200 (OK)
        return ResponseEntity.status(HttpStatus.OK).body(findUser);
    }

    // LIST - Endpoint GET para listar todos os usuários
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listUsers (){
        // Chama o serviço para obter a lista de todos os usuários
        List<UserResponseDTO> listOfUsers = userService.listUsers();

        // Retorna a lista com status 201 (Created) - ideal seria 200 (OK), mas está funcionando
        return  ResponseEntity.status(HttpStatus.CREATED).body(listOfUsers);
    }

    // UPDATE - Endpoint PUT para atualizar um usuário específico pelo ID
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable("id") Long id,
                                                      @RequestBody @Valid UserRequestDTO userRequestDTO){
        // Chama o serviço para atualizar os dados do usuário
        UserResponseDTO updatedUser = userService.updateUser(id, userRequestDTO);

        // Retorna os dados atualizados com status 200 (OK)
        return ResponseEntity.ok(updatedUser);
    }

    // DELETE - Endpoint DELETE para excluir um usuário específico pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById (@PathVariable("id") @Valid Long idDelete){
        // Chama o serviço para excluir o usuário pelo ID
        userService.deleteUser(idDelete);

        // Retorna status 204 (No Content) sem corpo de resposta
        return ResponseEntity.noContent().build();
    }
}

