package br.com.softpethouse.api.service.account;

import br.com.softpethouse.api.service.account.dto.AccountDtoCreate;
import br.com.softpethouse.api.service.account.dto.AccountDtoOut;
import br.com.softpethouse.api.service.account.dto.AccountDtoUpdate;
import br.com.softpethouse.api.service.validation.EntityNotFoundException;

import java.util.List;

public interface AccountService {

    List<AccountDtoOut> accounts();

    AccountDtoOut accounts(long id) throws EntityNotFoundException;

    long create(AccountDtoCreate dto) throws EntityNotFoundException;

    boolean update(long id, AccountDtoUpdate dto) throws EntityNotFoundException;

    boolean disable(long id) throws EntityNotFoundException;

}
