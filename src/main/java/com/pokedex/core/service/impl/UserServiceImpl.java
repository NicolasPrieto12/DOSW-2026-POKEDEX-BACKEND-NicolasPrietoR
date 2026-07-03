package com.pokedex.core.service.impl;

import com.pokedex.core.exception.ResourceNotFoundException;
import com.pokedex.core.model.User;
import com.pokedex.core.service.interfaces.UserPersistencePort;
import com.pokedex.core.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserPersistencePort userPort;

    @Override
    public Page<User> findAll(Pageable pageable) {
        log.debug("Listando usuarios");
        return org.springframework.data.domain.Page.empty(pageable);
    }

    @Override
    public User findById(Long id) {
        log.debug("Buscando usuario con id: {}", id);
        return userPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @Override
    public User findByEmail(String email) {
        log.debug("Buscando usuario con email: {}", email);
        return userPort.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
    }

    @Override
    @Transactional
    public User update(Long id, User user) {
        log.debug("Actualizando usuario con id: {}", id);
        findById(id);
        return userPort.save(user.toBuilder().id(id).build());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.debug("Eliminando usuario con id: {}", id);
        findById(id);
        userPort.deleteById(id);
    }

    @Override
    @Transactional
    public void toggleActive(Long id) {
        log.debug("Cambiando estado activo de usuario con id: {}", id);
        User user = findById(id);
        userPort.save(user.toBuilder().active(!user.getActive()).build());
    }
}
