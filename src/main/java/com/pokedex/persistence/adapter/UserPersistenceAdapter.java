package com.pokedex.persistence.adapter;

import com.pokedex.core.model.User;
import com.pokedex.core.service.interfaces.UserPersistencePort;
import com.pokedex.persistence.mapper.UserPersistenceMapper;
import com.pokedex.persistence.repository.relational.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserPersistencePort {

    private final UserJpaRepository repository;
    private final UserPersistenceMapper mapper;

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public boolean existsByTrainerName(String trainerName) {
        return repository.existsByTrainerName(trainerName);
    }

    @Override
    public User save(User user) {
        return mapper.toDomain(repository.save(mapper.toEntity(user)));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
