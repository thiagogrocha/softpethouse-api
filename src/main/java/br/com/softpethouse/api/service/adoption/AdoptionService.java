package br.com.softpethouse.api.service.adoption;

import br.com.softpethouse.api.service.adoption.dto.AdoptionDtoCreate;
import br.com.softpethouse.api.service.adoption.dto.AdoptionDtoOut;
import br.com.softpethouse.api.service.adoption.dto.AdoptionDtoUpdate;
import br.com.softpethouse.api.service.validation.EntityNotFoundException;

import java.util.List;

public interface AdoptionService {

    List<AdoptionDtoOut> adoptions();

    AdoptionDtoOut adoptions(long id) throws EntityNotFoundException;

    long create(AdoptionDtoCreate dto) throws EntityNotFoundException;

    boolean update(long id, AdoptionDtoUpdate dto) throws EntityNotFoundException;

    boolean disable(long id) throws EntityNotFoundException;

}
