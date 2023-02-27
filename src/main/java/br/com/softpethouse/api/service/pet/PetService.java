package br.com.softpethouse.api.service.pet;

import br.com.softpethouse.api.domain.pet.PetEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PetService implements PanacheRepository<PetEntity> {
}
