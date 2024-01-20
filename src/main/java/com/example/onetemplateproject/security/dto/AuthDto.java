package com.example.onetemplateproject.security.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthDto {

    @NotBlank
    private String login;

    @NotBlank
    private String password;

    @NotBlank
    private String fio;

    private String role;

    private String status;
}
