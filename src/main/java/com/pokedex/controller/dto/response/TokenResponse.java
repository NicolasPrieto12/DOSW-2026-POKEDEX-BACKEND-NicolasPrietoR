package com.pokedex.controller.dto.response;

public record TokenResponse(

    String accessToken,
    String tokenType,
    Long expiresIn

) {}
