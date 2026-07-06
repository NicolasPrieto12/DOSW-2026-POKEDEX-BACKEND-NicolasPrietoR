package com.pokedex.persistence.adapter;

import com.pokedex.core.model.Pokemon;
import com.pokedex.core.model.PokemonFilterCriteria;
import com.pokedex.core.service.interfaces.PokemonPersistencePort;
import com.pokedex.persistence.entity.relational.PokemonEntity;
import com.pokedex.persistence.entity.relational.RegionEntity;
import com.pokedex.persistence.entity.relational.TypeEntity;
import com.pokedex.persistence.mapper.PokemonPersistenceMapper;
import com.pokedex.persistence.repository.relational.PokemonJpaRepository;
import com.pokedex.persistence.repository.relational.RegionJpaRepository;
import com.pokedex.persistence.repository.relational.TypeJpaRepository;
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
    private final TypeJpaRepository typeRepository;
    private final RegionJpaRepository regionRepository;
    private final PokemonPersistenceMapper mapper;

    @Override
    public Optional<Pokemon> findById(Long id) {
        return repository.findByIdWithTypesAndStats(id)
                .map(mapper::toDomain);
    }

    @Override
    public Page<Pokemon> findAll(Pageable pageable) {
        return repository.findAllWithRelations(pageable)
                .map(mapper::toDomain);
    }

    @Override
    public boolean existsByNationalNumber(Integer number) {
        return repository.existsByNationalNumber(number);
    }

    @Override
    public Pokemon save(Pokemon pokemon) {
        PokemonEntity entity = mapper.toEntity(pokemon);

        List<TypeEntity> types = pokemon.getTypes() == null ? List.of() :
                pokemon.getTypes().stream()
                        .map(name -> typeRepository.findByNameIgnoreCase(name).orElse(null))
                        .filter(t -> t != null)
                        .toList();

        RegionEntity region = pokemon.getRegion() == null ? null :
                regionRepository.findByNameIgnoreCase(pokemon.getRegion()).orElse(null);

        PokemonEntity toSave = PokemonEntity.builder()
                .nationalNumber(entity.getNationalNumber())
                .name(entity.getName())
                .imageUrl(entity.getImageUrl())
                .description(entity.getDescription())
                .generation(entity.getGeneration())
                .types(types)
                .region(region)
                .build();

        PokemonEntity saved = repository.save(toSave);
        return repository.findByIdWithTypesAndStats(saved.getId())
                .map(mapper::toDomain)
                .orElseThrow();
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
