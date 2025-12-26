package com.example.Clinica.security.user.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record AuthCreateUserRequest(
        @NotBlank String email,
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String password,
        @Valid AuthCreateRoleRequest roleRequest

) {
}
