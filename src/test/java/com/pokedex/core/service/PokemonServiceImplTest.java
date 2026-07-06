package com.pokedex.core.service;

import com.pokedex.core.exception.DuplicateResourceException;
import com.pokedex.core.exception.ResourceNotFoundException;
import com.pokedex.core.model.Pokemon;
import com.pokedex.core.model.PokemonStats;
import com.pokedex.core.service.impl.PokemonServiceImpl;
import com.pokedex.core.service.interfaces.PokemonPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PokemonServiceImplTest {

    @Mock
    private PokemonPersistencePort pokemonPort;

    @InjectMocks
    private PokemonServiceImpl service;

    private Pokemon pikachu;

    @BeforeEach
    void setUp() {
        pikachu = Pokemon.builder()
                .id(1L)
                .nationalNumber(25)
                .name("Pikachu")
                .types(List.of("Electric"))
                .region("Kanto")
                .generation(1)
                .hasMega(false)
                .stats(PokemonStats.builder()
                        .hp(35)
                        .attack(55)
                        .defense(40)
                        .specialAttack(50)
                        .specialDefense(50)
                        .speed(90)
                        .build())
                .build();
    }

    @Test
    @DisplayName("findById: debe retornar el Pokemon cuando existe")
    void findById_whenExists_returnsP() {
        when(pokemonPort.findById(1L)).thenReturn(Optional.of(pikachu));

        Pokemon result = service.findById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Pikachu");
        verify(pokemonPort).findById(1L);
    }

    @Test
    @DisplayName("findById: debe lanzar ResourceNotFoundException cuando no existe")
    void findById_whenNotFound_throws() {
        when(pokemonPort.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.findById(99L));
    }

    @Test
    @DisplayName("create: debe lanzar DuplicateResourceException si el numero ya existe")
    void create_whenDuplicate_throws() {
        when(pokemonPort.existsByNationalNumber(25)).thenReturn(true);

        assertThrows(DuplicateResourceException.class, () -> service.create(pikachu));

        verify(pokemonPort, never()).save(any());
    }

    @Test
    @DisplayName("create: debe guardar y retornar el Pokemon cuando no existe duplicado")
    void create_whenNew_returnsSaved() {
        when(pokemonPort.existsByNationalNumber(25)).thenReturn(false);
        when(pokemonPort.save(pikachu)).thenReturn(pikachu);

        Pokemon result = service.create(pikachu);

        assertThat(result.getName()).isEqualTo("Pikachu");
        verify(pokemonPort).save(pikachu);
    }

    @Test
    @DisplayName("delete: debe lanzar ResourceNotFoundException si no existe")
    void delete_whenNotFound_throws() {
        when(pokemonPort.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.delete(99L));

        verify(pokemonPort, never()).deleteById(any());
    }
}
