package br.com.softpethouse.api.service.user;

import br.com.softpethouse.api.service.user.dto.UserDtoCreate;
import br.com.softpethouse.api.domain.user.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class UserService implements PanacheRepository<UserEntity> {

    public UserEntity create(UserDtoCreate dto) {
        UserEntity entity = UserDtoCreate.toEntity(dto);

        persist(entity);

        return entity;
    }

}
