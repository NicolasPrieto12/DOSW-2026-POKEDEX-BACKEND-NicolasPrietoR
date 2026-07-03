package com.pokedex.persistence.repository.relational;

import com.pokedex.persistence.entity.relational.PokemonEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface PokemonJpaRepository extends JpaRepository<PokemonEntity, Long>,
        JpaSpecificationExecutor<PokemonEntity> {

    boolean existsByNationalNumber(Integer nationalNumber);

    @EntityGraph(attributePaths = {"types", "stats", "region"})
    Optional<PokemonEntity> findByIdWithTypesAndStats(Long id);

    Optional<PokemonEntity> findByNationalNumber(Integer nationalNumber);
}
