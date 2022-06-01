package br.com.softpethouse.api.pet.service;

import br.com.softpethouse.api.pet.entity.PetEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PetService implements PanacheRepository<PetEntity> {
}
