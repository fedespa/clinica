package com.example.Clinica.security.user.dto;

public record AuthResponse(
        String username,
        String message,
        String jwt,
        Boolean status) {
}
