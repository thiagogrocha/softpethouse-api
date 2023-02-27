package br.com.softpethouse.api.infra.repository.account;

import br.com.softpethouse.api.service.account.dto.AccountDtoCreate;
import br.com.softpethouse.api.service.account.dto.AccountDtoOut;
import br.com.softpethouse.api.service.account.dto.AccountDtoUpdate;
import br.com.softpethouse.api.domain.account.AccountEntity;
import br.com.softpethouse.api.domain.account.TypeAccountEntity;
import br.com.softpethouse.api.service.account.AccountService;
import br.com.softpethouse.api.domain.business.BusinessEntity;
import br.com.softpethouse.api.service.business.BusinessService;
import br.com.softpethouse.api.service.validation.EntityNotFoundException;
import br.com.softpethouse.api.domain.user.UserEntity;
import br.com.softpethouse.api.service.user.UserService;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class AccountRepository implements AccountService, PanacheRepository<AccountEntity> {

    private String accountNotFound = "Conta não encontrada! Id: ";

    private String typeAccountNotFound = "Tipo de Conta não encontrada! Id: ";

    private String businessNotFound = "Negócio não encontrado! Id: ";

    @Inject
    UserService userService;

    @Inject
    TypeAccountRepository typeAccountRepository;

    @Inject
    BusinessService businessService;

    @Override
    public List<AccountDtoOut> accounts() {
        return findAll(Sort.by("username", Sort.Direction.Ascending)).list()
                .stream().map(AccountDtoOut::toDto).collect(Collectors.toList());
    }

    @Override
    public AccountDtoOut accounts(long id) throws EntityNotFoundException {
        AccountEntity entity = findByIdOptional(id)
                .orElseThrow(() -> new EntityNotFoundException(accountNotFound + id));

        return AccountDtoOut.toDto(entity);
    }

    @Override
    public long create(AccountDtoCreate dto) throws EntityNotFoundException {
        TypeAccountEntity typeAccount = typeAccountRepository.findByIdOptional(dto.getTypeAccountId())
                .orElseThrow(() -> new EntityNotFoundException(typeAccountNotFound + dto.getTypeAccountId()));

        BusinessEntity business = null;
        if (dto.getBusinessId().isPresent())
            business = businessService.findByIdOptional(dto.getBusinessId().get())
                    .orElseThrow(() -> new EntityNotFoundException(businessNotFound + dto.getBusinessId()));

        UserEntity user = userService.create(dto.getUser());

        AccountEntity account = AccountDtoCreate.toEntity(dto, typeAccount, business, user);

        persist(account);

        return account.getId();
    }

    @Override
    public boolean update(long id, AccountDtoUpdate dto) throws EntityNotFoundException {
        AccountEntity entity = findByIdOptional(id)
                .orElseThrow(() -> new EntityNotFoundException(accountNotFound + id));

        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());

        return true;
    }

    @Override
    public boolean disable(long id) throws EntityNotFoundException {
        AccountEntity entity = findByIdOptional(id)
                .orElseThrow(() -> new EntityNotFoundException(accountNotFound + id));

        entity.setActive("N");

        return true;
    }

}
