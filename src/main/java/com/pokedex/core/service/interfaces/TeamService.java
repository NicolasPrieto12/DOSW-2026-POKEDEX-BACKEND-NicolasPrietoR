package com.pokedex.core.service.interfaces;

import com.pokedex.core.model.Team;

import java.util.List;

public interface TeamService {

    List<Team> findByUserId(Long userId);

    Team findById(Long id);

    Team create(Long userId, Team team);

    Team update(Long id, Team team);

    void delete(Long id);
}
