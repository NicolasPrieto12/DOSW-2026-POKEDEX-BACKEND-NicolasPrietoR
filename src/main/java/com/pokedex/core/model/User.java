package com.pokedex.core.model;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder(toBuilder = true)
public class User {

    Long id;
    String email;
    String trainerName;
    String profilePicture;
    String role;
    Boolean active;
    LocalDateTime createdAt;
}
