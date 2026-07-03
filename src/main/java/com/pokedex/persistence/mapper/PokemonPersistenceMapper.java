package com.pokedex.persistence.mapper;

import com.pokedex.core.model.Pokemon;
import com.pokedex.core.model.PokemonStats;
import com.pokedex.persistence.entity.relational.PokemonEntity;
import com.pokedex.persistence.entity.relational.PokemonStatEntity;
import com.pokedex.persistence.entity.relational.TypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PokemonPersistenceMapper {

    @Mapping(target = "types", expression = "java(mapTypes(entity.getTypes()))")
    @Mapping(target = "region", expression = "java(entity.getRegion() != null ? entity.getRegion().getName() : null)")
    @Mapping(target = "stats", source = "stats")
    @Mapping(target = "hasMega", ignore = true)
    Pokemon toDomain(PokemonEntity entity);

    PokemonStats toDomain(PokemonStatEntity statsEntity);

    @Mapping(target = "types", ignore = true)
    @Mapping(target = "region", ignore = true)
    @Mapping(target = "stats", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    PokemonEntity toEntity(Pokemon pokemon);

    default List<String> mapTypes(List<TypeEntity> types) {
        if (types == null) return List.of();
        return types.stream().map(TypeEntity::getName).toList();
    }
}
