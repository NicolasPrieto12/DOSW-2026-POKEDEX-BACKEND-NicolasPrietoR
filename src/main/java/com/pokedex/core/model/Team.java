package com.pokedex.core.model;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder(toBuilder = true)
public class Team {

    Long id;
    String name;
    User owner;
    List<Pokemon> members;
    Boolean deleted;
    LocalDateTime createdAt;
}
