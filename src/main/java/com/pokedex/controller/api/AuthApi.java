package com.pokedex.controller.api;

import com.pokedex.controller.dto.response.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Auth", description = "Autenticación con Google OAuth2")
@RequestMapping("/v1/auth")
public interface AuthApi {

    @Operation(summary = "Callback OAuth2 Google", description = "Recibe el código de Google y retorna JWT")
    @ApiResponse(responseCode = "200", description = "Token generado")
    @GetMapping("/callback")
    ResponseEntity<TokenResponse> callback(@RequestParam String code);
}
