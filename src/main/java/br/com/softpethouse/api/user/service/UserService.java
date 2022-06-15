package br.com.softpethouse.api.user.service;

import br.com.softpethouse.api.user.dto.UserDto;
import br.com.softpethouse.api.user.entity.UserEntity;
import br.com.softpethouse.api.user.mapper.UserMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UserService implements PanacheRepository<UserEntity> {

    @Inject
    UserMapper mapper;

    public UserEntity create(UserDto dto) {
        UserEntity entity = mapper.toEntity(dto);

        persist(entity);

        return entity;
    }

}
