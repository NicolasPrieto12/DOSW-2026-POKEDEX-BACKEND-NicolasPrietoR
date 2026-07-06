package com.pokedex.core.service;

import com.pokedex.core.exception.ResourceNotFoundException;
import com.pokedex.core.model.User;
import com.pokedex.core.service.impl.UserServiceImpl;
import com.pokedex.core.service.interfaces.UserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserPersistencePort userPort;

    @InjectMocks
    private UserServiceImpl service;

    private User ash;

    @BeforeEach
    void setUp() {
        ash = User.builder().id(1L).email("ash@kanto.com").trainerName("Ash").role("TRAINER").active(true).build();
    }

    @Test
    @DisplayName("findById: retorna usuario cuando existe")
    void findById_whenExists_returnsUser() {
        when(userPort.findById(1L)).thenReturn(Optional.of(ash));

        User result = service.findById(1L);

        assertThat(result.getEmail()).isEqualTo("ash@kanto.com");
    }

    @Test
    @DisplayName("findById: lanza excepcion cuando no existe")
    void findById_whenNotFound_throws() {
        when(userPort.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.findById(99L));
    }

    @Test
    @DisplayName("findByEmail: retorna usuario cuando existe")
    void findByEmail_whenExists_returnsUser() {
        when(userPort.findByEmail("ash@kanto.com")).thenReturn(Optional.of(ash));

        User result = service.findByEmail("ash@kanto.com");

        assertThat(result.getTrainerName()).isEqualTo("Ash");
    }

    @Test
    @DisplayName("findByEmail: lanza excepcion cuando no existe")
    void findByEmail_whenNotFound_throws() {
        when(userPort.findByEmail("unknown@test.com")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.findByEmail("unknown@test.com"));
    }

    @Test
    @DisplayName("findOrCreateByEmail: retorna existente si ya existe")
    void findOrCreateByEmail_whenExists_returnsExisting() {
        when(userPort.findByEmail("ash@kanto.com")).thenReturn(Optional.of(ash));

        User result = service.findOrCreateByEmail("ash@kanto.com", "Ash");

        assertThat(result.getId()).isEqualTo(1L);
        verify(userPort, never()).save(any());
    }

    @Test
    @DisplayName("findOrCreateByEmail: crea nuevo usuario si no existe")
    void findOrCreateByEmail_whenNotExists_createsNew() {
        when(userPort.findByEmail("misty@kanto.com")).thenReturn(Optional.empty());
        when(userPort.save(any())).thenReturn(ash);

        User result = service.findOrCreateByEmail("misty@kanto.com", "Misty");

        verify(userPort).save(any());
    }

    @Test
    @DisplayName("update: actualiza usuario cuando existe")
    void update_whenExists_returnsUpdated() {
        when(userPort.findById(1L)).thenReturn(Optional.of(ash));
        when(userPort.save(any())).thenReturn(ash);

        User result = service.update(1L, ash);

        verify(userPort).save(any());
    }

    @Test
    @DisplayName("update: lanza excepcion cuando no existe")
    void update_whenNotFound_throws() {
        when(userPort.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.update(99L, ash));
        verify(userPort, never()).save(any());
    }

    @Test
    @DisplayName("delete: elimina usuario cuando existe")
    void delete_whenExists_deletes() {
        when(userPort.findById(1L)).thenReturn(Optional.of(ash));

        service.delete(1L);

        verify(userPort).deleteById(1L);
    }

    @Test
    @DisplayName("delete: lanza excepcion cuando no existe")
    void delete_whenNotFound_throws() {
        when(userPort.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.delete(99L));
        verify(userPort, never()).deleteById(any());
    }

    @Test
    @DisplayName("toggleActive: cambia estado activo del usuario")
    void toggleActive_whenExists_toggles() {
        when(userPort.findById(1L)).thenReturn(Optional.of(ash));
        when(userPort.save(any())).thenReturn(ash);

        service.toggleActive(1L);

        verify(userPort).save(any());
    }

    @Test
    @DisplayName("findAll: retorna pagina vacia")
    void findAll_returnsPage() {
        var pageable = PageRequest.of(0, 10);

        var result = service.findAll(pageable);

        assertThat(result).isNotNull();
    }
}
