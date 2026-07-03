package com.pokedex.controller.mapper;

import com.pokedex.controller.dto.response.UserResponse;
import com.pokedex.core.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    UserResponse toResponse(User user);
}
