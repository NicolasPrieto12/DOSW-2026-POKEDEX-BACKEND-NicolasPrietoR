package com.pokedex.controller.api;

import com.pokedex.controller.dto.request.LoginRequest;
import com.pokedex.controller.dto.response.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth", description = "Autenticación")
@RequestMapping("/v1/auth")
public interface AuthApi {

    @Operation(summary = "Callback OAuth2 Google")
    @ApiResponse(responseCode = "200", description = "Token generado")
    @GetMapping("/callback")
    ResponseEntity<TokenResponse> callback(@RequestParam String code);

    @Operation(summary = "Login con email y password")
    @ApiResponse(responseCode = "200", description = "Token generado")
    @PostMapping("/login")
    ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request);
}
