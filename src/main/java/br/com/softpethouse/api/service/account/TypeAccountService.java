package br.com.softpethouse.api.service.account;

import br.com.softpethouse.api.service.account.dto.TypeAccountDtoCreate;
import br.com.softpethouse.api.service.account.dto.TypeAccountDtoOut;
import br.com.softpethouse.api.service.validation.EntityNotFoundException;

import java.util.List;

public interface TypeAccountService {

    List<TypeAccountDtoOut> typesAccount() throws EntityNotFoundException;

    TypeAccountDtoOut typesAccount(long id) throws EntityNotFoundException;

    long create(TypeAccountDtoCreate dto);

    boolean update(long id, TypeAccountDtoCreate dto) throws EntityNotFoundException;

    boolean disable(long id) throws EntityNotFoundException;

}
