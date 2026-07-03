package com.pokedex.persistence.adapter;

import com.pokedex.core.model.Pokemon;
import com.pokedex.core.model.PokemonFilterCriteria;
import com.pokedex.core.service.interfaces.PokemonPersistencePort;
import com.pokedex.persistence.mapper.PokemonPersistenceMapper;
import com.pokedex.persistence.repository.relational.PokemonJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PokemonPersistenceAdapter implements PokemonPersistencePort {

    private final PokemonJpaRepository repository;
    private final PokemonPersistenceMapper mapper;

    @Override
    public Optional<Pokemon> findById(Long id) {
        return repository.findByIdWithTypesAndStats(id)
                .map(mapper::toDomain);
    }

    @Override
    public Page<Pokemon> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toDomain);
    }

    @Override
    public boolean existsByNationalNumber(Integer number) {
        return repository.existsByNationalNumber(number);
    }

    @Override
    public Pokemon save(Pokemon pokemon) {
        return mapper.toDomain(repository.save(mapper.toEntity(pokemon)));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Pokemon> findByCriteria(PokemonFilterCriteria criteria) {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }
}
