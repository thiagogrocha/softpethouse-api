package br.com.softpethouse.api.account.service;

import lombok.extern.slf4j.Slf4j;
import br.com.softpethouse.api.Resources;
import br.com.softpethouse.api.account.dto.TypeAccountDto;
import br.com.softpethouse.api.account.entity.TypeAccountEntity;
import br.com.softpethouse.api.account.mapper.TypeAccountMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Slf4j
@Transactional
@ApplicationScoped
public class TypeAccountService implements PanacheRepository<TypeAccountEntity> {

    @Inject
    TypeAccountMapper mapper;

    public Response typesAccount() {
        return Response.ok(mapper.toDtoList(findAll(Sort.by("name", Sort.Direction.Ascending)).list())).build();
    }

    public Response typesAccount(long id) {
        TypeAccountEntity entity = findById(id);

        if (entity == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(mapper.toDto(entity)).build();
    }

    public Response create(TypeAccountDto dto) {
        TypeAccountEntity entity = mapper.toEntity(dto);

        persist(entity);

        return Response.created(UriBuilder.fromPath(Resources.TYPEACCOUNT).path(entity.getId().toString()).build()).build();
    }

    public Response update(long id, TypeAccountDto dto) {
        TypeAccountEntity entity = findById(id);

        if (entity == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        mapper.updateEntityFromDto(dto, entity);

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
