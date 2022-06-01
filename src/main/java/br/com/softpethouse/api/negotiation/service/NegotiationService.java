package br.com.softpethouse.api.negotiation.service;

import br.com.softpethouse.api.negotiation.entity.NegotiationEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NegotiationService implements PanacheRepository<NegotiationEntity> {
}
