package com.example.Clinica.security.user.controller;

import com.example.Clinica.security.user.dto.AuthLoginRequest;
import com.example.Clinica.security.user.dto.AuthResponse;
import com.example.Clinica.security.user.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest authLoginRequest) {

        AuthResponse response = this.authService.loginUser(authLoginRequest);
        return ResponseEntity.ok(response);
    }


}
