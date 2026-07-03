package com.pokedex.controller.mapper;

import com.pokedex.controller.dto.request.PokemonRequest;
import com.pokedex.controller.dto.response.PokemonResponse;
import com.pokedex.controller.dto.response.PokemonStatsResponse;
import com.pokedex.core.model.Pokemon;
import com.pokedex.core.model.PokemonStats;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PokemonDtoMapper {

    @Mapping(target = "stats", source = "stats")
    PokemonResponse toResponse(Pokemon pokemon);

    PokemonStatsResponse toStatsResponse(PokemonStats stats);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "hasMega", constant = "false")
    @Mapping(target = "stats", ignore = true)
    Pokemon toDomain(PokemonRequest request);

    List<PokemonResponse> toResponseList(List<Pokemon> pokemons);
}
