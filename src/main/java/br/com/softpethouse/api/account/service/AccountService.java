package br.com.softpethouse.api.account.service;

import br.com.softpethouse.api.Resources;
import br.com.softpethouse.api.account.dto.AccountDtoCreate;
import br.com.softpethouse.api.account.dto.AccountDtoOut;
import br.com.softpethouse.api.account.dto.AccountDtoUpdate;
import br.com.softpethouse.api.account.entity.AccountEntity;
import br.com.softpethouse.api.business.entity.BusinessEntity;
import br.com.softpethouse.api.account.entity.TypeAccountEntity;
import br.com.softpethouse.api.commom.validation.ResponseError;
import br.com.softpethouse.api.commom.validation.ResponseMsg;
import br.com.softpethouse.api.user.entity.UserEntity;
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
public class AccountService implements PanacheRepository<AccountEntity> {

    private Validator validator;
    private AccountService accountService;
    private TypeAccountService typeAccountService;
    private BusinessService businessService;

    @Inject
    public AccountService(Validator validator, AccountService accountService, TypeAccountService typeAccountService, BusinessService businessService) {
        this.validator = validator;
        this.accountService = accountService;
        this.typeAccountService = typeAccountService;
        this.businessService = businessService;
    }

    public Response create(AccountDtoCreate dto) {
        Set<ConstraintViolation<AccountDtoCreate>> violations = validator.validate(dto);

        if (!violations.isEmpty())
            return ResponseError
                    .createFromValidation(violations)
                    .withStatusCode(ResponseError.UNPROCESSABLE_ENTITY_STATUS);

        if (dto.getUser() == null)
            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).entity(new ResponseMsg("Usuário não encontrada!")).build();

        TypeAccountEntity typeAccount = typeAccountService.findById(dto.getIdTypeAccount());

        if (typeAccount == null)
            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).entity(new ResponseMsg("Tipo de Conta não encontrada!")).build();

        BusinessEntity business = businessService.findById(dto.getIdBusiness());

        if (business == null)
            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).entity(new ResponseMsg("Negócio não encontrado!")).build();

        UserEntity user = new UserEntity(dto.getUser().getName(), dto.getUser().getAge());

        AccountEntity entity = new AccountEntity(user, typeAccount, business, dto.getUsername(), dto.getEmail(), dto.getPassword());
        persist(entity);

        return Response.created(UriBuilder.fromPath(Resources.ACCOUNT).path(entity.getId().toString()).build()).build();
    }

    public Response update(long id, AccountDtoUpdate dto) {
        AccountEntity account = accountService.findById(id);

        if (account == null)
            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).entity(new ResponseMsg("Conta não encontrada!")).build();

        Set<ConstraintViolation<AccountDtoUpdate>> violations = validator.validate(dto);

        if (!violations.isEmpty())
            return ResponseError
                    .createFromValidation(violations)
                    .withStatusCode(ResponseError.UNPROCESSABLE_ENTITY_STATUS);

        TypeAccountEntity typeAccount = typeAccountService.findById(dto.getIdTypeAccount());

        if (typeAccount == null)
            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).entity(new ResponseMsg("Tipo de Conta não encontrada!")).build();

        BusinessEntity business = businessService.findById(dto.getIdBusiness());

        if (business == null)
            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).entity(new ResponseMsg("Negócio não encontrado!")).build();

        account.setTypeAccount(typeAccount);
        account.setBusiness(business);
        account.setUsername(dto.getUserName());
        account.setPassword(dto.getPassword());

        return Response.status(Response.Status.OK.getStatusCode()).entity(new ResponseMsg("Conta atualizada!")).build();
    }

    public Response disable(long id) {
        AccountEntity account = accountService.findById(id);

        if (account == null)
            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).entity(new ResponseMsg("Conta não encontrada!")).build();

        account.setActive("N");

        return Response.status(Response.Status.NO_CONTENT.getStatusCode()).entity(new ResponseMsg("Conta desativada com sucesso!")).build();
    }

    public Response accounts() {
        return Response.ok().entity(findAll(Sort.by("username", Sort.Direction.Ascending))
                .stream()
                .map(AccountDtoOut::fromEntity)
                .collect(Collectors.toList())).build();
    }

    public Response accounts(long id) {
        AccountEntity account = accountService.findById(id);

        if (account == null)
            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).entity(new ResponseMsg("Conta não encontrada!")).build();

        return Response.ok().entity(AccountDtoOut.fromEntity(account)).build();
    }
}
