package com.example.Clinica.security.user.service;

import com.example.Clinica.security.jwt.JwtUtils;
import com.example.Clinica.security.user.dto.AuthLoginRequest;
import com.example.Clinica.security.user.dto.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;


    public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {
        String email = authLoginRequest.email();
        String password = authLoginRequest.password();

        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = this.jwtUtils.createToken(authentication);

        return new AuthResponse(email, "Usuario logueado exitosamente!", accessToken, true);
    }

}
