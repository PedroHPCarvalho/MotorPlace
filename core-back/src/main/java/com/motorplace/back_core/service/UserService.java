package com.motorplace.back_core.service;

import com.motorplace.back_core.dto.UserRequestDTO;
import com.motorplace.back_core.dto.UserResponseDTO;
import com.motorplace.back_core.entity.User;
import com.motorplace.back_core.mapper.UserMapper;
import com.motorplace.back_core.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }



    public UserResponseDTO createUser(UserRequestDTO dto){
        User user = userMapper.toEntity(dto);
        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }

    public List<UserResponseDTO> listUsers() {
        return userRepository.findAll().stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    public UserResponseDTO findByUsername (String username){
        return userRepository.findByUsernameRepo(username);
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO user){
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        User updatedUserInst = existingUser.toBuilder()
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .build();

        return  userMapper.toDTO(userRepository.save(updatedUserInst));
    }

    public void deleteUser(Long id){
        if (!userRepository.existsById(id)){
            throw new RuntimeException("Usuário não encontrado");
        }
        userRepository.deleteById(id);
    }
}