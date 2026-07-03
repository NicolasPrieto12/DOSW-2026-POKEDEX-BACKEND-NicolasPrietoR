package com.pokedex.core.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PokemonFilterCriteria {

    String type;
    String region;
    Integer generation;
    String name;
}
