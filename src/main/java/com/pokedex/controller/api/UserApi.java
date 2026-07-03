package com.pokedex.controller.api;

import com.pokedex.controller.dto.request.UserUpdateRequest;
import com.pokedex.controller.dto.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Users", description = "Gestión de usuarios")
@RequestMapping("/v1/users")
@SecurityRequirement(name = "Bearer Authentication")
public interface UserApi {

    @Operation(summary = "Obtener perfil propio")
    @ApiResponse(responseCode = "200", description = "Perfil obtenido")
    @GetMapping("/me")
    ResponseEntity<UserResponse> getMe();

    @Operation(summary = "Actualizar perfil propio")
    @ApiResponse(responseCode = "200", description = "Perfil actualizado")
    @PutMapping("/me")
    ResponseEntity<UserResponse> updateMe(@Valid @RequestBody UserUpdateRequest request);

    @Operation(summary = "Listar usuarios", description = "Solo ADMIN")
    @GetMapping
    ResponseEntity<?> findAll(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size);

    @Operation(summary = "Bloquear/desbloquear usuario", description = "Solo ADMIN")
    @PatchMapping("/{id}/toggle-active")
    ResponseEntity<Void> toggleActive(@PathVariable Long id);

    @Operation(summary = "Eliminar usuario", description = "Solo ADMIN")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);
}
