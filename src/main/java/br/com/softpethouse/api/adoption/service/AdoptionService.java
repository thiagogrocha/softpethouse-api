package br.com.softpethouse.api.adoption.service;

import br.com.softpethouse.api.Resources;
import br.com.softpethouse.api.adoption.dto.AdoptionDto;
import br.com.softpethouse.api.adoption.mapper.AdoptionMapper;
import io.quarkus.panache.common.Sort;
import lombok.extern.slf4j.Slf4j;
import br.com.softpethouse.api.adoption.entity.AdoptionEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Slf4j
@ApplicationScoped
public class AdoptionService implements PanacheRepository<AdoptionEntity> {

    @Inject
    AdoptionMapper mapper;
    
    public Response adoptions() {
        return Response.ok(mapper.toDtoList(findAll(Sort.by("dateTime", Sort.Direction.Ascending)).list())).build();
    }

    public Response adoptions(long id) {
        AdoptionEntity entity = findById(id);

        if (entity == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(mapper.toDto(entity)).build();
    }

    public Response create(AdoptionDto dto) {
        AdoptionEntity entity = mapper.toEntity(dto);

        persist(entity);

        return Response.created(UriBuilder.fromPath(Resources.ADOPTION).path(entity.getId().toString()).build()).build();
    }

    public Response update(long id, AdoptionDto dto) {
        AdoptionEntity entity = findById(id);

        if (entity == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        mapper.updateEntityFromDto(dto, entity);

        return Response.ok().build();
    }

    public Response disable(long id) {
        AdoptionEntity entity = findById(id);

        if (entity == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        entity.setActive("N");

        return Response.noContent().build();
    }

}
