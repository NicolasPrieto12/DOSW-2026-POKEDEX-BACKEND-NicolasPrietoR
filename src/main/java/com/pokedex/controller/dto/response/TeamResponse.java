package com.pokedex.controller.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record TeamResponse(

    Long id,
    String name,
    List<PokemonResponse> members,
    LocalDateTime createdAt

) {}
