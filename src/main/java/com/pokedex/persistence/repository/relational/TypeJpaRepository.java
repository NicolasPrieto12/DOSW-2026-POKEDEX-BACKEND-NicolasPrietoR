package com.pokedex.persistence.repository.relational;

import com.pokedex.persistence.entity.relational.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeJpaRepository extends JpaRepository<TypeEntity, Long> {
    Optional<TypeEntity> findByNameIgnoreCase(String name);
}
