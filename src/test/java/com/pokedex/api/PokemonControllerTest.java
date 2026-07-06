package com.pokedex.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokedex.controller.dto.request.PokemonRequest;
import com.pokedex.controller.dto.response.PokemonResponse;
import com.pokedex.controller.mapper.PokemonDtoMapper;
import com.pokedex.core.exception.ResourceNotFoundException;
import com.pokedex.core.model.Pokemon;
import com.pokedex.core.service.interfaces.PokemonService;
import com.pokedex.core.service.interfaces.UserPersistencePort;
import com.pokedex.persistence.repository.document.PokemonViewMongoRepository;
import com.pokedex.persistence.repository.relational.PokemonJpaRepository;
import com.pokedex.persistence.repository.relational.UserJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "spring.autoconfigure.exclude=" +
        "org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration," +
        "org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration",
        "spring.flyway.enabled=false",
        "spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect"
})
class PokemonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private PokemonService pokemonService;

    @MockitoBean
    private PokemonDtoMapper mapper;

    // Mocks para cortar la cadena de dependencias hacia la BD
    @MockitoBean
    private UserJpaRepository userJpaRepository;

    @MockitoBean
    private PokemonJpaRepository pokemonJpaRepository;

    @MockitoBean
    private PokemonViewMongoRepository pokemonViewMongoRepository;

    @MockitoBean
    private UserPersistencePort userPersistencePort;

    private PokemonResponse pikachuResponse() {
        return new PokemonResponse(1L, 25, "Pikachu", null, null, List.of("Electric"), "Kanto", 1, false, null);
    }

    @Test
    void findById_returns200() throws Exception {
        Pokemon pokemon = Pokemon.builder().id(1L).name("Pikachu").build();

        when(pokemonService.findById(1L)).thenReturn(pokemon);
        when(mapper.toResponse(pokemon)).thenReturn(pikachuResponse());

        mockMvc.perform(get("/v1/pokemon/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Pikachu"))
                .andExpect(jsonPath("$.types[0]").value("Electric"));
    }

    @Test
    void findById_whenNotFound_returns404() throws Exception {
        when(pokemonService.findById(99L)).thenThrow(new ResourceNotFoundException("Pokemon", "id", 99L));

        mockMvc.perform(get("/v1/pokemon/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void findAll_returns200() throws Exception {
        Pokemon pokemon = Pokemon.builder().id(1L).name("Pikachu").build();

        when(pokemonService.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(pokemon)));
        when(mapper.toResponse(any(Pokemon.class))).thenReturn(pikachuResponse());

        mockMvc.perform(get("/v1/pokemon"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void create_withInvalidBody_returns400() throws Exception {
        PokemonRequest invalid = new PokemonRequest(null, "", null, null, List.of(), null, null);

        mockMvc.perform(post("/v1/pokemon")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalid)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void create_withValidBody_returns201() throws Exception {
        PokemonRequest request = new PokemonRequest(25, "Pikachu", "url", "desc", List.of("ELECTRIC"), "KANTO", 1);
        Pokemon pokemon = Pokemon.builder().id(1L).name("Pikachu").build();

        when(mapper.toDomain(any())).thenReturn(pokemon);
        when(pokemonService.create(any())).thenReturn(pokemon);
        when(mapper.toResponse(any())).thenReturn(pikachuResponse());

        mockMvc.perform(post("/v1/pokemon")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Pikachu"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void update_returns200() throws Exception {
        PokemonRequest request = new PokemonRequest(25, "Pikachu", "url", "desc", List.of("ELECTRIC"), "KANTO", 1);
        Pokemon pokemon = Pokemon.builder().id(1L).name("Pikachu").build();

        when(mapper.toDomain(any())).thenReturn(pokemon);
        when(pokemonService.update(any(), any())).thenReturn(pokemon);
        when(mapper.toResponse(any())).thenReturn(pikachuResponse());

        mockMvc.perform(put("/v1/pokemon/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Pikachu"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void delete_returns204() throws Exception {
        mockMvc.perform(delete("/v1/pokemon/1"))
                .andExpect(status().isNoContent());
    }
}
