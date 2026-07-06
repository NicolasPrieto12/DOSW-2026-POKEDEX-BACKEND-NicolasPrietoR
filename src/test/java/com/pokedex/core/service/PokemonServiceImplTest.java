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

    @Test
    @DisplayName("delete: debe eliminar el Pokemon cuando existe")
    void delete_whenExists_deletesSuccessfully() {
        when(pokemonPort.findById(1L)).thenReturn(Optional.of(pikachu));

        service.delete(1L);

        verify(pokemonPort).deleteById(1L);
    }

    @Test
    @DisplayName("update: debe actualizar y retornar el Pokemon cuando existe")
    void update_whenExists_returnsUpdated() {
        Pokemon updated = pikachu.toBuilder().name("Raichu").build();
        when(pokemonPort.findById(1L)).thenReturn(Optional.of(pikachu));
        when(pokemonPort.save(any())).thenReturn(updated);

        Pokemon result = service.update(1L, pikachu);

        assertThat(result.getName()).isEqualTo("Raichu");
        verify(pokemonPort).save(any());
    }

    @Test
    @DisplayName("update: debe lanzar ResourceNotFoundException si no existe")
    void update_whenNotFound_throws() {
        when(pokemonPort.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.update(99L, pikachu));

        verify(pokemonPort, never()).save(any());
    }

    @Test
    @DisplayName("findAll: debe retornar página de pokemons")
    void findAll_returnsPage() {
        org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(0, 10);
        org.springframework.data.domain.Page<Pokemon> page = new org.springframework.data.domain.PageImpl<>(List.of(pikachu));
        when(pokemonPort.findAll(pageable)).thenReturn(page);

        org.springframework.data.domain.Page<Pokemon> result = service.findAll(pageable);

        assertThat(result.getContent()).hasSize(1);
        verify(pokemonPort).findAll(pageable);
    }

    @Test
    @DisplayName("filterByCriteria: debe retornar lista filtrada")
    void filterByCriteria_returnsList() {
        com.pokedex.core.model.PokemonFilterCriteria criteria = com.pokedex.core.model.PokemonFilterCriteria.builder().build();
        when(pokemonPort.findByCriteria(criteria)).thenReturn(List.of(pikachu));

        List<Pokemon> result = service.filterByCriteria(criteria);

        assertThat(result).hasSize(1);
        verify(pokemonPort).findByCriteria(criteria);
    }
}
