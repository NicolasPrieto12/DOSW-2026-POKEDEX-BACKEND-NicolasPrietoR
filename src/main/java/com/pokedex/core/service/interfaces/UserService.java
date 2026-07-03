package com.pokedex.core.service.interfaces;

import com.pokedex.core.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<User> findAll(Pageable pageable);

    User findById(Long id);

    User findByEmail(String email);

    User update(Long id, User user);

    void delete(Long id);

    void toggleActive(Long id);
}
