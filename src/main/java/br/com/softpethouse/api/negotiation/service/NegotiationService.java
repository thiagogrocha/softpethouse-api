package br.com.softpethouse.api.negotiation.service;

import br.com.softpethouse.api.Resources;
import br.com.softpethouse.api.business.entity.BusinessEntity;
import br.com.softpethouse.api.business.service.BusinessService;
import br.com.softpethouse.api.commom.validation.ResponseMsg;
import br.com.softpethouse.api.negotiation.dto.NegotiationDtoCreate;
import br.com.softpethouse.api.negotiation.dto.NegotiationDtoUpdate;
import br.com.softpethouse.api.negotiation.entity.NegotiationEntity;
import br.com.softpethouse.api.negotiation.entity.NegotiationItemEntity;
import br.com.softpethouse.api.product.entity.ProductEntity;
import br.com.softpethouse.api.user.entity.UserEntity;
import br.com.softpethouse.api.user.service.UserService;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ApplicationScoped
public class NegotiationService implements PanacheRepository<NegotiationEntity> {

    @Inject
    UserService userService;

    @Inject
    BusinessService businessService;

    public Response negotiations() {
        return Response.ok(findAll().list()).build();
    }

    public Response negotiations(long id) {
        NegotiationEntity negotiation = findById(id);

        if (negotiation == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(negotiation).build();
    }

    @Transactional
    public Response create(NegotiationDtoCreate dto) {
        UserEntity user = userService.findById(dto.getUserId());
        if (user == null)
            return Response.status(Response.Status.NOT_FOUND).entity(new ResponseMsg("Negociação não encontrado!")).build();

        BusinessEntity business = businessService.findById(dto.getBusinessId());
        if (business == null)
            return Response.status(Response.Status.NOT_FOUND).entity(new ResponseMsg("Negócio não encontrado!")).build();

        List<NegotiationItemEntity> negotiationItems = dto.getNegotiationItems()
                .stream().map(dtoCreate -> NegotiationItemEntity.builder()
                        .product(ProductEntity.toEntity(dtoCreate.get))
                        .build()).collect(Collectors.toList());

        NegotiationEntity negotiation = NegotiationEntity.toEntity(dto, user, business);
        persist(negotiation);

        return Response.created(UriBuilder.fromPath(Resources.BUSINESS).path(negotiation.getId().toString()).build()).build();
    }

    @Transactional
    public Response update(long id, NegotiationDtoUpdate dto) {
        NegotiationEntity negotiation = findById(id);
        if (negotiation == null)
            return Response.status(Response.Status.NOT_FOUND).entity(new ResponseMsg("Negociação não encontrado!")).build();

        UserEntity user = userService.findById(dto.getUserId());
        if (user == null)
            return Response.status(Response.Status.NOT_FOUND).entity(new ResponseMsg("Usuário não encontrado!")).build();

        BusinessEntity business = businessService.findById(dto.getBusinessId());
        if (business == null)
            return Response.status(Response.Status.NOT_FOUND).entity(new ResponseMsg("Negócio não encontrado!")).build();

        negotiation.setUser(user);
        negotiation.setBusiness(business);
        negotiation.setDescription(dto.getDescription());
        negotiation.setValue(dto.getValue());

        return Response.ok().build();
    }

    @Transactional
    public Response disable(long id) {
        NegotiationEntity negotiation = findById(id);

        if (negotiation == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        negotiation.setActive("N");

        return Response.noContent().build();
    }

}
