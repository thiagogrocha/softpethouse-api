package br.com.softpethouse.api.account.service;

import br.com.softpethouse.api.Resources;
import br.com.softpethouse.api.account.dto.AccountDtoIn;
import br.com.softpethouse.api.account.entity.AccountEntity;
import br.com.softpethouse.api.account.entity.BusinessEntity;
import br.com.softpethouse.api.account.entity.TypeAccountEntity;
import br.com.softpethouse.api.commom.validation.ResponseError;
import br.com.softpethouse.api.commom.validation.ResponseMsg;
import br.com.softpethouse.api.user.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.Set;

@Transactional
@ApplicationScoped
public class AccountService implements PanacheRepository<AccountEntity> {

    private Validator validator;
    private TypeAccountService typeAccountService;
    private BusinessService businessService;

    @Inject
    public AccountService(Validator validator, TypeAccountService typeAccountService, BusinessService businessService) {
        this.validator = validator;
        this.typeAccountService = typeAccountService;
        this.businessService = businessService;
    }

    public Response save(AccountDtoIn dto) {
        Set<ConstraintViolation<AccountDtoIn>> violations = validator.validate(dto);

        if (!violations.isEmpty())
            return ResponseError
                    .createFromValidation(violations)
                    .withStatusCode(ResponseError.UNPROCESSABLE_ENTITY_STATUS);

       if (dto.getUserDto() == null)
           return Response.status(Response.Status.NOT_FOUND.getStatusCode()).entity(new ResponseMsg("Usuário não encontrada!")).build();

        TypeAccountEntity typeAccount = typeAccountService.findById(dto.getIdTypeAccount());

        if (typeAccount == null)
            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).entity(new ResponseMsg("Tipo de Conta não encontrada!")).build();

        BusinessEntity business = businessService.findById(dto.getIdBusiness());

        if (business == null)
            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).entity(new ResponseMsg("Negócio não encontrado!")).build();

        UserEntity user = new UserEntity(dto.getUserDto().getName(), dto.getUserDto().getAge());

        AccountEntity entity = new AccountEntity(user, typeAccount, business, dto.getNickName(), dto.getEmail(), dto.getPassword());
        persist(entity);

        return Response.created(UriBuilder.fromPath(Resources.ACCOUNT).path(entity.getId().toString()).build()).build();
    }
}
