package br.com.softpethouse.api.user.service;

import br.com.softpethouse.api.user.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserService implements PanacheRepository<UserEntity> {
}
