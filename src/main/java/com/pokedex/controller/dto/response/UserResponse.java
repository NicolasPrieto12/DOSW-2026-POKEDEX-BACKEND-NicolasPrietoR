package com.pokedex.controller.dto.response;

public record UserResponse(

    Long id,
    String email,
    String trainerName,
    String profilePicture,
    String role,
    Boolean active

) {}
