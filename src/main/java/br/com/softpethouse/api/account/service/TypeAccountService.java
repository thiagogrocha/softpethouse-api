package br.com.softpethouse.api.account.service;

import br.com.softpethouse.api.account.entity.TypeAccountEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TypeAccountService implements PanacheRepository<TypeAccountEntity> {
}
