package br.com.softpethouse.api.adoption.service;

import br.com.softpethouse.api.Resources;
import br.com.softpethouse.api.adoption.dto.AdoptionDto;
import br.com.softpethouse.api.adoption.dto.AdoptionDtoCreate;
import br.com.softpethouse.api.commom.validation.ResponseMsg;
import br.com.softpethouse.api.pet.entity.PetEntity;
import br.com.softpethouse.api.pet.service.PetService;
import br.com.softpethouse.api.user.entity.UserEntity;
import br.com.softpethouse.api.user.service.UserService;
import io.quarkus.panache.common.Sort;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@RequestScoped
public class AdoptionService implements PanacheRepository<AdoptionEntity> {

    @Inject
    PetService petService;

    @Inject
    UserService userService;


    public Response adoptions() {
        return Response.ok(findAll(Sort.by("dateTime", Sort.Direction.Ascending)).list()
                .stream().map(AdoptionDto::toDto).collect(Collectors.toList())).build();
    }

    public Response adoptions(long id) {
        AdoptionEntity entity = findById(id);

        if (entity == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(AdoptionDto.toDto(entity)).build();
    }

    public Response create(AdoptionDtoCreate dto) {
        PetEntity pet = petService.findById(dto.getIdPet());
        if (pet == null)
            return Response.status(Response.Status.NOT_FOUND).entity(new ResponseMsg("Pet não encontrado!")).build();

        UserEntity userOld = userService.findById(dto.getIdUserOld());
        if (userOld == null)
            return Response.status(Response.Status.NOT_FOUND).entity(new ResponseMsg("Usuário nativo do Pet não encontrado!")).build();

        UserEntity userNew = userService.findById(dto.getIdUserNew());
        if (userNew == null)
            return Response.status(Response.Status.NOT_FOUND).entity(new ResponseMsg("Usuário de destino do Pet não encontrado!")).build();

        AdoptionEntity adoption = AdoptionEntity.toEntity(pet, userOld, userNew, dto.getDateTime());

        persist(adoption);

        return Response.created(UriBuilder.fromPath(Resources.ADOPTION).path(adoption.getId().toString()).build()).build();
    }

    public Response update(long id, AdoptionDtoCreate dto) {
        AdoptionEntity adoption = findById(id);
        if (adoption == null)
            return Response.status(Response.Status.NOT_FOUND).entity(new ResponseMsg("Adoção não encontrada!")).build();

        PetEntity pet = petService.findById(dto.getIdPet());
        if (pet == null)
            return Response.status(Response.Status.NOT_FOUND).entity(new ResponseMsg("Pet não encontrado!")).build();

        UserEntity userOld = userService.findById(dto.getIdUserOld());
        if (userOld == null)
            return Response.status(Response.Status.NOT_FOUND).entity(new ResponseMsg("Usuário nativo do Pet não encontrado!")).build();

        UserEntity userNew = userService.findById(dto.getIdUserNew());
        if (userNew == null)
            return Response.status(Response.Status.NOT_FOUND).entity(new ResponseMsg("Usuário de destino do Pet não encontrado!")).build();

        adoption.setPet(pet);
        adoption.setUserOld(userOld);
        adoption.setUserNew(userNew);
        adoption.setDateTime(dto.getDateTime());

        return Response.ok().build();
    }

    public Response disable(long id) {
        AdoptionEntity adoption = findById(id);

        if (adoption == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        adoption.setActive("N");

        return Response.noContent().build();
    }

}
