package br.com.softpethouse.api.account.service;

import br.com.softpethouse.api.Resources;
import br.com.softpethouse.api.account.dto.AccountDtoCreate;
import br.com.softpethouse.api.account.dto.AccountDtoOut;
import br.com.softpethouse.api.account.dto.AccountDtoUpdate;
import br.com.softpethouse.api.account.entity.AccountEntity;
import br.com.softpethouse.api.business.entity.BusinessEntity;
import br.com.softpethouse.api.account.entity.TypeAccountEntity;
import br.com.softpethouse.api.business.service.BusinessService;
import br.com.softpethouse.api.commom.validation.ResponseError;
import br.com.softpethouse.api.commom.validation.ResponseMsg;
import br.com.softpethouse.api.user.dto.UserDto;
import br.com.softpethouse.api.user.service.UserService;
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
    private UserService userService;
    private TypeAccountService typeAccountService;
    private BusinessService businessService;

    @Inject
    public AccountService(Validator validator, UserService userService, TypeAccountService typeAccountService, BusinessService businessService) {
        this.validator = validator;
        this.userService = userService;
        this.typeAccountService = typeAccountService;
        this.businessService = businessService;
    }

    public Response accounts() {
        return Response.ok().entity(findAll(Sort.by("username", Sort.Direction.Ascending))
                .stream()
                .map(AccountDtoOut::fromEntity)
                .collect(Collectors.toList())).build();
    }

    public Response accounts(long id) {
        AccountEntity entity = findById(id);

        if (entity == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok().entity(AccountDtoOut.fromEntity(entity)).build();
    }

    public Response create(AccountDtoCreate dto) {
        Set<ConstraintViolation<AccountDtoCreate>> violationsAcc = validator.validate(dto);

        if (!violationsAcc.isEmpty())
            return ResponseError
                    .createFromValidation(violationsAcc)
                    .withStatusCode(ResponseError.UNPROCESSABLE_ENTITY_STATUS);

        Set<ConstraintViolation<UserDto>> violationsUser = validator.validate(dto.getUser());

        if (!violationsUser.isEmpty())
            return ResponseError
                    .createFromValidation(violationsUser)
                    .withStatusCode(ResponseError.UNPROCESSABLE_ENTITY_STATUS);

        TypeAccountEntity typeAccount = typeAccountService.findById(dto.getIdTypeAccount());

        if (typeAccount == null)
            return Response.status(Response.Status.NOT_FOUND).entity(new ResponseMsg("Tipo de Conta não encontrada!")).build();

        BusinessEntity business = businessService.findById(dto.getIdBusiness());

        if (business == null)
            return Response.status(Response.Status.NOT_FOUND).entity(new ResponseMsg("Negócio não encontrado!")).build();

        AccountEntity entity = AccountEntity.builder()
                .user(userService.create(dto.getUser().getName(), dto.getUser().getAge()))
                .typeAccount(typeAccount)
                .business(business)
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword()).build();

        persist(entity);

        return Response.created(UriBuilder.fromPath(Resources.ACCOUNT).path(entity.getId().toString()).build()).build();
    }

    public Response update(long id, AccountDtoUpdate dto) {
        AccountEntity account = findById(id);

        if (account == null)
            return Response.status(Response.Status.NOT_FOUND).entity(new ResponseMsg("Conta não encontrada!")).build();

        Set<ConstraintViolation<AccountDtoUpdate>> violations = validator.validate(dto);

        if (!violations.isEmpty())
            return ResponseError
                    .createFromValidation(violations)
                    .withStatusCode(ResponseError.UNPROCESSABLE_ENTITY_STATUS);

        TypeAccountEntity typeAccount = typeAccountService.findById(dto.getIdTypeAccount());

        if (typeAccount == null)
            return Response.status(Response.Status.NOT_FOUND).entity(new ResponseMsg("Tipo de Conta não encontrada!")).build();

        BusinessEntity business = businessService.findById(dto.getIdBusiness());

        if (business == null)
            return Response.status(Response.Status.NOT_FOUND).entity(new ResponseMsg("Negócio não encontrado!")).build();

        account.setTypeAccount(typeAccount);
        account.setBusiness(business);
        account.setUsername(dto.getUserName());
        account.setPassword(dto.getPassword());

        return Response.ok().build();
    }

    public Response disable(long id) {
        AccountEntity entity = findById(id);

        if (entity == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        entity.setActive("N");

        return Response.noContent().build();
    }
}
