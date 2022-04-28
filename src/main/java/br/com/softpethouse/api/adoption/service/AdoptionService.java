package br.com.softpethouse.api.adoption.service;

import br.com.softpethouse.api.adoption.AdoptionEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AdoptionService implements PanacheRepository<AdoptionEntity> {
}
