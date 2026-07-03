package com.pokedex.persistence.mapper;

import com.pokedex.core.model.User;
import com.pokedex.persistence.entity.relational.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {

    User toDomain(UserEntity entity);

    UserEntity toEntity(User user);
}
