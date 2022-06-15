package br.com.softpethouse.api.account.service;

import lombok.extern.slf4j.Slf4j;
import br.com.softpethouse.api.Resources;
import br.com.softpethouse.api.account.dto.AccountDtoCreate;
import br.com.softpethouse.api.account.dto.AccountDtoUpdate;
import br.com.softpethouse.api.account.entity.AccountEntity;
import br.com.softpethouse.api.account.mapper.AccountMapper;
import br.com.softpethouse.api.business.entity.BusinessEntity;
import br.com.softpethouse.api.account.entity.TypeAccountEntity;
import br.com.softpethouse.api.business.service.BusinessService;
import br.com.softpethouse.api.commom.validation.ResponseMsg;
import br.com.softpethouse.api.user.service.UserService;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Slf4j
@Transactional
@ApplicationScoped
public class AccountService implements PanacheRepository<AccountEntity> {

    @Inject
    AccountMapper mapper;

    @Inject
    UserService userService;

    @Inject
    TypeAccountService typeAccountService;

    @Inject
    BusinessService businessService;

    public Response accounts() {
        return Response.ok(mapper.toDtoList(findAll(Sort.by("username", Sort.Direction.Ascending)).list())).build();
    }

    public Response accounts(long id) {
        AccountEntity entity = findById(id);

        if (entity == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(mapper.toDto(entity)).build();
    }

    public Response create(AccountDtoCreate dto) {
        TypeAccountEntity typeAccount = typeAccountService.findById(dto.getIdTypeAccount());

        if (typeAccount == null)
            return Response.status(Response.Status.NOT_FOUND).entity(new ResponseMsg("Tipo de Conta não encontrada!")).build();

        BusinessEntity business = businessService.findById(dto.getIdBusiness());

        if (business == null)
            return Response.status(Response.Status.NOT_FOUND).entity(new ResponseMsg("Negócio não encontrado!")).build();

        AccountEntity entity = mapper.toEntity(dto);
        entity.setUser(userService.create(dto.getUser()));

        persist(entity);

        return Response.created(UriBuilder.fromPath(Resources.ACCOUNT).path(entity.getId().toString()).build()).build();
    }

    public Response update(long id, AccountDtoUpdate dto) {
        AccountEntity account = findById(id);

        if (account == null)
            return Response.status(Response.Status.NOT_FOUND).entity(new ResponseMsg("Conta não encontrada!")).build();

        TypeAccountEntity typeAccount = typeAccountService.findById(dto.getIdTypeAccount());

        if (typeAccount == null)
            return Response.status(Response.Status.NOT_FOUND).entity(new ResponseMsg("Tipo de Conta não encontrada!")).build();

        BusinessEntity business = businessService.findById(dto.getIdBusiness());

        if (business == null)
            return Response.status(Response.Status.NOT_FOUND).entity(new ResponseMsg("Negócio não encontrado!")).build();

        mapper.updateEntityFromDto(dto, account);

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
