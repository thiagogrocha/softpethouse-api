package br.com.softpethouse.api.account.service;

import br.com.softpethouse.api.Resources;
import br.com.softpethouse.api.account.dto.AccountDtoOut;
import br.com.softpethouse.api.account.dto.TypeAccountDto;
import br.com.softpethouse.api.account.entity.AccountEntity;
import br.com.softpethouse.api.account.entity.TypeAccountEntity;
import br.com.softpethouse.api.commom.validation.ResponseError;
import br.com.softpethouse.api.commom.validation.ResponseMsg;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;

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
public class TypeAccountService implements PanacheRepository<TypeAccountEntity> {

    private Validator validator;

    @Inject
    public TypeAccountService(Validator validator) {
        this.validator = validator;
    }

    public Response typesAccount(){
        return Response.ok().entity(findAll(Sort.by("name", Sort.Direction.Ascending))
                .stream()
                .map(TypeAccountDto::fromEntity)
                .collect(Collectors.toList())).build();
    }

    public Response typesAccount(long id) {
        TypeAccountEntity entity = findById(id);

        if (entity == null)
            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();

        return Response.ok().entity(TypeAccountDto.fromEntity(entity)).build();
    }

    public Response create(TypeAccountDto dto) {
        Set<ConstraintViolation<TypeAccountDto>> violations = validator.validate(dto);

        if (!violations.isEmpty())
            return ResponseError
                    .createFromValidation(violations)
                    .withStatusCode(ResponseError.UNPROCESSABLE_ENTITY_STATUS);

        TypeAccountEntity entity = TypeAccountEntity.builder()
                .name(dto.getName())
                .description(dto.getDescription()).build();

        persist(entity);

        return Response.created(UriBuilder.fromPath(Resources.TYPEACCOUNT).path(entity.getId().toString()).build()).build();
    }

    public Response update(long id, TypeAccountDto dto) {
        TypeAccountEntity entity = findById(id);

        if(entity == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        Set<ConstraintViolation<TypeAccountDto>> violations = validator.validate(dto);

        if (!violations.isEmpty())
            return ResponseError
                    .createFromValidation(violations)
                    .withStatusCode(ResponseError.UNPROCESSABLE_ENTITY_STATUS);

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());

        return Response.ok().build();
    }

    public Response disable(long id) {
        TypeAccountEntity entity = findById(id);

        if (entity == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        entity.setActive("N");

        return Response.noContent().build();
    }

}
