package com.pokedex.persistence.repository.relational;

import com.pokedex.persistence.entity.relational.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByTrainerName(String trainerName);
}
