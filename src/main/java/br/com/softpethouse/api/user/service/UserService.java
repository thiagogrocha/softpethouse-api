package br.com.softpethouse.api.user.service;

import br.com.softpethouse.api.user.entity.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserService implements PanacheRepository<UserEntity> {

    public UserEntity create(String name, int age) {
        UserEntity entity = new UserEntity(name, age);
        persist(entity);

        return entity;
    }

}
