package br.com.softpethouse.api.service.historic;

import br.com.softpethouse.api.infra.resource.Resources;
import br.com.softpethouse.api.service.historic.dto.HistoricDtoCreate;
import br.com.softpethouse.api.service.historic.dto.HistoricDtoOut;
import br.com.softpethouse.api.domain.historic.HistoricEntity;
import br.com.softpethouse.api.domain.pet.PetEntity;
import br.com.softpethouse.api.service.pet.PetService;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Slf4j
@Transactional
@RequestScoped
public class HistoricService implements PanacheRepository<HistoricEntity> {

    @Inject
    PetService petService;

    private String petNotFound = "Pet não encontrado! Id: ";

    public Response historic(long petId) {
        PetEntity pet = petService.findByIdOptional(petId)
                .orElseThrow(() -> new WebApplicationException(
                        petNotFound + petId, Response.Status.NOT_FOUND));

        return Response.ok(list("petid", pet.getId()).stream().map(HistoricDtoOut::toDto)).build();
    }

    public Response create(HistoricDtoCreate dto) {
        PetEntity pet = petService.findByIdOptional(dto.getPetId())
                .orElseThrow(() -> new WebApplicationException(
                        petNotFound + dto.getPetId(), Response.Status.NOT_FOUND));

        HistoricEntity historic = HistoricDtoCreate.toEntity(pet, dto);

        persist(historic);

        return Response.created(UriBuilder.fromPath(Resources.HISTORIC).path(historic.getId().toString()).build()).build();
    }

}
