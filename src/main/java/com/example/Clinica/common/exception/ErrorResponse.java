package com.example.Clinica.common.exception;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorResponse(
        Object message,
        int status,
        LocalDateTime timestamp,
        String path
) {
}
