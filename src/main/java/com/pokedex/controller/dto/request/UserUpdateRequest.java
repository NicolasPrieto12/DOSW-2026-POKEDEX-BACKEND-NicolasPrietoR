package com.pokedex.controller.dto.request;

import jakarta.validation.constraints.Size;

public record UserUpdateRequest(

    @Size(max = 30, message = "El nombre no puede exceder 30 caracteres")
    String trainerName,

    String profilePicture

) {}
