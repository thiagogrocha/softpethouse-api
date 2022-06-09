package br.com.softpethouse.api.account.service;

import br.com.softpethouse.api.Resources;
import br.com.softpethouse.api.business.dto.BusinessDto;
import br.com.softpethouse.api.business.dto.BusinessDtoOut;
import br.com.softpethouse.api.business.entity.BusinessEntity;
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
import java.util.stream.Collectors;

@Transactional
@ApplicationScoped
public class BusinessService implements PanacheRepository<BusinessEntity> {

    private Validator validator;

    @Inject
    public BusinessService(Validator validator) {
        this.validator = validator;
    }

    public Response business() {
        return Response.ok(findAll()
                .stream()
                .map(BusinessDtoOut::fromEntity)
                .collect(Collectors.toList())).build();
    }

    public Response business(long id) {
        BusinessEntity business = findById(id);

        if (business == null)
            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();

        return Response.ok().entity(BusinessDtoOut.fromEntity(business)).build();
    }

    public Response create(BusinessDto dto) {
        Set<ConstraintViolation<BusinessDto>> violations = validator.validate(dto);

        if (!violations.isEmpty())
            return ResponseError
                    .createFromValidation(violations)
                    .withStatusCode(ResponseError.UNPROCESSABLE_ENTITY_STATUS);

        BusinessEntity entity = BusinessEntity.builder()
                .name(dto.getName())
                .description(dto.getDescription()).build();

        persist(entity);

        return Response.created(UriBuilder.fromPath(Resources.BUSINESS).path(entity.getId().toString()).build()).build();
    }

    public Response update(long id, BusinessDto dto) {
        BusinessEntity business = findById(id);

        if (business == null)
            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();

        Set<ConstraintViolation<BusinessDto>> violations = validator.validate(dto);

        if (!violations.isEmpty())
            return ResponseError
                    .createFromValidation(violations)
                    .withStatusCode(ResponseError.UNPROCESSABLE_ENTITY_STATUS);

        business.setName(dto.getName());
        business.setDescription(dto.getDescription());

        return Response.ok().build();
    }

    public Response disable(long id) {
        BusinessEntity business = findById(id);

        if (business == null)
            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();

        business.setActive("N");

        return Response.noContent().build();
    }

}
