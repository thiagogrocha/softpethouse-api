package br.com.softpethouse.api.business.service;

import lombok.extern.slf4j.Slf4j;
import br.com.softpethouse.api.Resources;
import br.com.softpethouse.api.business.dto.BusinessDto;
import br.com.softpethouse.api.business.entity.BusinessEntity;
import br.com.softpethouse.api.business.mapper.BusinessMapper;
import br.com.softpethouse.api.commom.validation.ResponseError;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.Set;

@Slf4j
@Transactional
@ApplicationScoped
public class BusinessService implements PanacheRepository<BusinessEntity> {

    @Inject
    BusinessMapper mapper;

    public Response business() {
        return Response.ok(mapper.toDtoList(findAll().list())).build();
    }

    public Response business(long id) {
        BusinessEntity entity = findById(id);

        if (entity == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(mapper.toDto(entity)).build();
    }

    public Response create(BusinessDto dto) {
        BusinessEntity entity = mapper.toEntity(dto);

        persist(entity);

        return Response.created(UriBuilder.fromPath(Resources.BUSINESS).path(entity.getId().toString()).build()).build();
    }

    public Response update(long id, BusinessDto dto) {
        BusinessEntity entity = findById(id);

        if (entity == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        mapper.updateEntityFromDto(dto, entity);

        return Response.ok().build();
    }

    public Response disable(long id) {
        BusinessEntity entity = findById(id);

        if (entity == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        entity.setActive("N");

        return Response.noContent().build();
    }

}
