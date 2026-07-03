package com.pokedex.core.service.impl;

import com.pokedex.core.exception.DuplicateResourceException;
import com.pokedex.core.exception.ResourceNotFoundException;
import com.pokedex.core.model.Pokemon;
import com.pokedex.core.model.PokemonFilterCriteria;
import com.pokedex.core.service.interfaces.PokemonPersistencePort;
import com.pokedex.core.service.interfaces.PokemonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PokemonServiceImpl implements PokemonService {

    private final PokemonPersistencePort pokemonPort;

    @Override
    public Page<Pokemon> findAll(Pageable pageable) {
        log.debug("Listando Pokemon pagina: {}", pageable.getPageNumber());
        return pokemonPort.findAll(pageable);
    }

    @Override
    public Pokemon findById(Long id) {
        log.debug("Buscando Pokemon con id: {}", id);
        return pokemonPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pokemon", "id", id));
    }

    @Override
    public Pokemon findByNationalNumber(Integer number) {
        log.debug("Buscando Pokemon con numero: {}", number);
        return pokemonPort.findById(number.longValue())
                .orElseThrow(() -> new ResourceNotFoundException("Pokemon", "nationalNumber", number));
    }

    @Override
    @Transactional
    public Pokemon create(Pokemon pokemon) {
        log.debug("Creando Pokemon: {}", pokemon.getName());
        if (pokemonPort.existsByNationalNumber(pokemon.getNationalNumber())) {
            throw new DuplicateResourceException("Pokemon", "nationalNumber", pokemon.getNationalNumber());
        }
        return pokemonPort.save(pokemon);
    }

    @Override
    @Transactional
    public Pokemon update(Long id, Pokemon pokemon) {
        log.debug("Actualizando Pokemon con id: {}", id);
        findById(id);
        return pokemonPort.save(pokemon.toBuilder().id(id).build());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.debug("Eliminando Pokemon con id: {}", id);
        findById(id);
        pokemonPort.deleteById(id);
    }

    @Override
    public List<Pokemon> filterByCriteria(PokemonFilterCriteria criteria) {
        log.debug("Filtrando Pokemon por criterios: {}", criteria);
        return pokemonPort.findByCriteria(criteria);
    }
}
