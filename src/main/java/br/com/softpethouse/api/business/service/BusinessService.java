package br.com.softpethouse.api.business.service;

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

@Transactional
@ApplicationScoped
public class BusinessService implements PanacheRepository<BusinessEntity> {

    private Validator validator;

    private BusinessMapper mapper;

    @Inject
    public BusinessService(Validator validator, BusinessMapper mapper) {
        this.validator = validator;
        this.mapper = mapper;
    }

    public Response business() {
        return Response.ok(mapper.toDtoList(findAll().list())).build();
    }

    public Response business(long id) {
        BusinessEntity entity = findById(id);

        if (entity == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok().entity(mapper.toDto(entity)).build();
    }

    public Response create(BusinessDto dto) {
        Set<ConstraintViolation<BusinessDto>> violations = validator.validate(dto);

        if (!violations.isEmpty())
            return ResponseError
                    .createFromValidation(violations)
                    .withStatusCode(ResponseError.UNPROCESSABLE_ENTITY_STATUS);

        BusinessEntity entity = mapper.toEntity(dto);

        persist(entity);

        return Response.created(UriBuilder.fromPath(Resources.BUSINESS).path(entity.getId().toString()).build()).build();
    }

    public Response update(long id, BusinessDto dto) {
        BusinessEntity entity = findById(id);

        if (entity == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        Set<ConstraintViolation<BusinessDto>> violations = validator.validate(dto);

        if (!violations.isEmpty())
            return ResponseError
                    .createFromValidation(violations)
                    .withStatusCode(ResponseError.UNPROCESSABLE_ENTITY_STATUS);

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
