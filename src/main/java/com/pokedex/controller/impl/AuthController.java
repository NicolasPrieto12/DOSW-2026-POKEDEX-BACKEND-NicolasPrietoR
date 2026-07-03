package com.pokedex.controller.impl;

import com.pokedex.controller.api.AuthApi;
import com.pokedex.controller.dto.request.LoginRequest;
import com.pokedex.controller.dto.response.TokenResponse;
import com.pokedex.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    @Override
    public ResponseEntity<TokenResponse> callback(String code) {
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<TokenResponse> login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.email());
        String token = jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new TokenResponse(token, "Bearer", 86400000L));
    }
}
