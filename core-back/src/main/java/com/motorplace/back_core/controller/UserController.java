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

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        log.info("Username: {}", userRequestDTO.getUsername());
        log.info("Password: {}", userRequestDTO.getPassword());
        UserResponseDTO createdUser = userService.createUserServ(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    // READ: buscar por ID
    @GetMapping("/{idSearch}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable("idSearch") Long idSearch) {
        UserResponseDTO findUser = userService.findById(idSearch);
        return ResponseEntity.ok(findUser);
    }

    // READ: buscar por username
    @GetMapping("/search")
    public ResponseEntity<UserResponseDTO> getUserByUsername (@RequestParam String username){
        UserResponseDTO findUser = userService.findByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body(findUser);
    }

    // LISTAR TODOS
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listUsers (){
        List<UserResponseDTO> listOfUsers = userService.listUsers();
        return  ResponseEntity.status(HttpStatus.CREATED).body(listOfUsers);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable("id") Long id,
       @RequestBody @Valid UserRequestDTO userRequestDTO){
        UserResponseDTO updatedUser = userService.updateUser(id, userRequestDTO);
        return ResponseEntity.ok(updatedUser);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById (@PathVariable("id") @Valid Long idDelete){
        userService.deleteUser(idDelete);
        return ResponseEntity.noContent().build();
    }


}
