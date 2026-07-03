package com.pokedex.core.service.interfaces;

import com.pokedex.core.model.User;

import java.util.Optional;

public interface UserPersistencePort {

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByTrainerName(String trainerName);

    User save(User user);

    void deleteById(Long id);
}
