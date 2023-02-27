package br.com.softpethouse.api.infra.repository.adoption;

import br.com.softpethouse.api.service.adoption.dto.AdoptionDtoCreate;
import br.com.softpethouse.api.service.adoption.dto.AdoptionDtoOut;
import br.com.softpethouse.api.service.adoption.dto.AdoptionDtoUpdate;
import br.com.softpethouse.api.domain.adoption.AdoptionEntity;
import br.com.softpethouse.api.service.adoption.AdoptionService;
import br.com.softpethouse.api.service.validation.EntityNotFoundException;
import br.com.softpethouse.api.domain.pet.PetEntity;
import br.com.softpethouse.api.service.pet.PetService;
import br.com.softpethouse.api.domain.user.UserEntity;
import br.com.softpethouse.api.service.user.UserService;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequestScoped
@Transactional
public class AdoptionRepository implements AdoptionService, PanacheRepository<AdoptionEntity> {

    private static final String adoptionNotFound = "Adoção não encontrada! Id: ";

    private static final String petNotFound = "Pet não encontrado! Id: ";

    private static final String userNewNotFound = "Usuário de destino do Pet não encontrado! Id: ";

    @Inject
    PetService petService;

    @Inject
    UserService userService;

    @Override
    public List<AdoptionDtoOut> adoptions() {
        return findAll(Sort.by("dateTime", Sort.Direction.Ascending)).list()
                .stream().map(AdoptionDtoOut::toDto).collect(Collectors.toList());
    }

    @Override
    public AdoptionDtoOut adoptions(long id) throws EntityNotFoundException {
        AdoptionEntity entity = findByIdOptional(id)
                .orElseThrow(() -> new EntityNotFoundException(adoptionNotFound + id));

        return AdoptionDtoOut.toDto(entity);
    }

    @Override
    public long create(AdoptionDtoCreate dto) throws EntityNotFoundException {
        PetEntity pet = petService.findByIdOptional(dto.getPetId())
                .orElseThrow(() -> new EntityNotFoundException(petNotFound + dto.getPetId()));

        UserEntity userNew = null;
        if (dto.getUserNewId().isPresent())
            userNew = userService.findByIdOptional(dto.getUserNewId().get())
                    .orElseThrow(() -> new EntityNotFoundException(userNewNotFound + dto.getUserNewId()));

        AdoptionEntity adoption = AdoptionDtoCreate.toEntity(pet, userNew);

        persist(adoption);

        return adoption.getId();
    }

    @Override
    public boolean update(long id, AdoptionDtoUpdate dto) throws EntityNotFoundException {
        AdoptionEntity adoption = findByIdOptional(id)
                .orElseThrow(() -> new EntityNotFoundException(adoptionNotFound + id));

        UserEntity userNew = userService.findByIdOptional(dto.getUserNewId())
                .orElseThrow(() -> new EntityNotFoundException(userNewNotFound + dto.getUserNewId()));

        adoption.setUserNew(userNew);

        return true;
    }

    @Override
    public boolean disable(long id) throws EntityNotFoundException {
        AdoptionEntity adoption = findByIdOptional(id)
                .orElseThrow(() -> new EntityNotFoundException(adoptionNotFound + id));

        adoption.setActive("N");

        return true;
    }

}
