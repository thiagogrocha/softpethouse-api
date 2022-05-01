package br.com.softpethouse.api.account.service;

import br.com.softpethouse.api.Resources;
import br.com.softpethouse.api.account.dto.BusinessDto;
import br.com.softpethouse.api.account.entity.BusinessEntity;
import br.com.softpethouse.api.account.resource.BusinessResource;
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

    @Inject
    public BusinessService(Validator validator) {
        this.validator = validator;
    }

    public Response save(BusinessDto dto) {
        Set<ConstraintViolation<BusinessDto>> violations = validator.validate(dto);

        if (!violations.isEmpty())
            return ResponseError
                    .createFromValidation(violations)
                    .withStatusCode(ResponseError.UNPROCESSABLE_ENTITY_STATUS);

        BusinessEntity entity = new BusinessEntity(dto.getName(), dto.getDescription());
        persist(entity);

        return Response.created(UriBuilder.fromPath(Resources.BUSINESS).path(entity.getId().toString()).build()).build();
    }

}
