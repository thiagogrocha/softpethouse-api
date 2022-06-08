package br.com.softpethouse.api.account.service;

import br.com.softpethouse.api.Resources;
import br.com.softpethouse.api.account.dto.TypeAccountDto;
import br.com.softpethouse.api.account.entity.TypeAccountEntity;
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
public class TypeAccountService implements PanacheRepository<TypeAccountEntity> {

    private Validator validator;

    @Inject
    public TypeAccountService(Validator validator) {
        this.validator = validator;
    }

    public Response save(TypeAccountDto dto) {
        Set<ConstraintViolation<TypeAccountDto>> violations = validator.validate(dto);

        if (!violations.isEmpty())
            return ResponseError
                    .createFromValidation(violations)
                    .withStatusCode(ResponseError.UNPROCESSABLE_ENTITY_STATUS);

        TypeAccountEntity entity = TypeAccountEntity.builder().name(dto.getName()).description(dto.getDescription()).build();
        persist(entity);

        return Response.created(UriBuilder.fromPath(Resources.TYPEACCOUNT).path(entity.getId().toString()).build()).build();
    }
}
