package com.pokedex.controller.impl;

import com.pokedex.controller.api.AuthApi;
import com.pokedex.controller.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    @Override
    public ResponseEntity<TokenResponse> callback(String code) {
        return ResponseEntity.ok().build();
    }
}
