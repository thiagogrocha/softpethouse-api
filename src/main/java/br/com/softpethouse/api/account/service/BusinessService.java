package br.com.softpethouse.api.account.service;

import br.com.softpethouse.api.account.entity.BusinessEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BusinessService implements PanacheRepository<BusinessEntity> {



}
