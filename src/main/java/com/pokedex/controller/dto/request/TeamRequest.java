package com.pokedex.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record TeamRequest(

    @NotBlank(message = "El nombre del equipo es obligatorio")
    @Size(max = 30, message = "El nombre no puede exceder 30 caracteres")
    String name,

    @NotEmpty(message = "El equipo debe tener al menos un Pokémon")
    @Size(max = 6, message = "El equipo no puede tener más de 6 Pokémon")
    List<Long> pokemonIds

) {}
