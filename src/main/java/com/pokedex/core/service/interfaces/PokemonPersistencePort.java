package com.pokedex.core.service.interfaces;

import com.pokedex.core.model.Pokemon;
import com.pokedex.core.model.PokemonFilterCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PokemonPersistencePort {

    Optional<Pokemon> findById(Long id);

    Page<Pokemon> findAll(Pageable pageable);

    boolean existsByNationalNumber(Integer number);

    Pokemon save(Pokemon pokemon);

    void deleteById(Long id);

    List<Pokemon> findByCriteria(PokemonFilterCriteria criteria);
}
