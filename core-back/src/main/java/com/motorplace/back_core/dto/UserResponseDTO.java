package com.motorplace.back_core.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.StringTokenizer;
@Getter
@Setter
public class UserResponseDTO {
    private Long id;
    private String username;
    private String password;
    private String role;
}
