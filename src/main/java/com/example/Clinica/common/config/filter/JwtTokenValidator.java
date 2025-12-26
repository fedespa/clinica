package com.example.Clinica.common.config.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.Clinica.security.jwt.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.Collection;

@Component
public class JwtTokenValidator extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final HandlerExceptionResolver resolver;

    public JwtTokenValidator(
            JwtUtils jwtUtils,
            @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver
    ) {
        this.jwtUtils = jwtUtils;
        this.resolver = resolver;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (jwtToken != null && jwtToken.startsWith("Bearer ")) {
            jwtToken = jwtToken.substring(7);

            DecodedJWT decodedJWT;
            try {
                decodedJWT = this.jwtUtils.validateToken(jwtToken);
            } catch(Exception e) {
                resolver.resolveException(request, response, null, e);
                return;
            }

            String username = this.jwtUtils.extractUsername(decodedJWT);
            String stringAuthorities = this.jwtUtils.getSpecificClaim(decodedJWT, "authorities").asString();

            Collection<? extends GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(stringAuthorities);

            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);

            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);

        }

        filterChain.doFilter(request, response);

    }
}
