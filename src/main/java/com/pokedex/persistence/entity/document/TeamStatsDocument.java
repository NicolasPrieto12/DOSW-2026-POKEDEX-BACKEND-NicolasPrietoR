package com.pokedex.persistence.entity.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "team_stats")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamStatsDocument {

    @Id
    private String id;

    @Field("pokemon_id")
    private Long pokemonId;

    @Field("pokemon_name")
    private String pokemonName;

    @Field("times_in_teams")
    @Builder.Default
    private Integer timesInTeams = 0;

    @Field("choice_rate")
    @Builder.Default
    private Double choiceRate = 0.0;

    @Field("last_updated")
    private LocalDateTime lastUpdated;
}
