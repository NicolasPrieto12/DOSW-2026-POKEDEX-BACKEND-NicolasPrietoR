package com.pokedex.controller.impl;

import com.pokedex.controller.api.UserApi;
import com.pokedex.controller.dto.request.UserUpdateRequest;
import com.pokedex.controller.dto.response.UserResponse;
import com.pokedex.controller.mapper.UserDtoMapper;
import com.pokedex.core.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;
    private final UserDtoMapper mapper;

    @Override
    public ResponseEntity<UserResponse> getMe() {
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<UserResponse> updateMe(UserUpdateRequest request) {
        return ResponseEntity.ok().build();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> findAll(int page, int size) {
        return ResponseEntity.ok(userService.findAll(PageRequest.of(page, size)).map(mapper::toResponse));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> toggleActive(Long id) {
        userService.toggleActive(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
