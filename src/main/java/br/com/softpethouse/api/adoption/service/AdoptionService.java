package br.com.softpethouse.api.adoption.service;

import lombok.extern.slf4j.Slf4j;
import br.com.softpethouse.api.adoption.entity.AdoptionEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@Slf4j
@ApplicationScoped
public class AdoptionService implements PanacheRepository<AdoptionEntity> {
}
