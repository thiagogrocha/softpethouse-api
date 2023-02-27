package br.com.softpethouse.api.service.negotiation;

import br.com.softpethouse.api.infra.resource.Resources;
import br.com.softpethouse.api.domain.business.BusinessEntity;
import br.com.softpethouse.api.domain.negotiation.NegotiationEntity;
import br.com.softpethouse.api.service.business.BusinessService;
import br.com.softpethouse.api.service.negotiation.dto.NegotiationDtoCreate;
import br.com.softpethouse.api.service.negotiation.dto.NegotiationDtoUpdate;
import br.com.softpethouse.api.service.negotiation.dto.NegotiationItemDtoIn;
import br.com.softpethouse.api.domain.user.UserEntity;
import br.com.softpethouse.api.service.user.UserService;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Slf4j
@Transactional
@RequestScoped
public class NegotiationService implements PanacheRepository<NegotiationEntity> {

    @Inject
    UserService userService;

    @Inject
    BusinessService businessService;

    @Inject
    NegotiationItemService negotiationItemService;

    private String businessNotFound = "Negócio não encontrado! Id: ";

    private String negotiationNotFound = "Negociação não encontrada! Id: ";

    private String userNotFound = "Usuário não encontrado! Id: ";

    public Response negotiations() {
        return Response.ok(findAll().list()).build();
    }

    public Response negotiations(long id) {
        NegotiationEntity negotiation = findByIdOptional(id)
                .orElseThrow(() -> new WebApplicationException(
                        negotiationNotFound + id, Response.Status.NOT_FOUND));

        return Response.ok(negotiation).build();
    }

    public Response create(NegotiationDtoCreate dto) {
        UserEntity user = userService.findByIdOptional(dto.getUserId())
                .orElseThrow(() -> new WebApplicationException(
                        userNotFound + dto.getUserId(), Response.Status.NOT_FOUND));

        BusinessEntity business = businessService.findByIdOptional(dto.getBusinessId())
                .orElseThrow(() -> new WebApplicationException(
                        businessNotFound + dto.getBusinessId(), Response.Status.NOT_FOUND));

        NegotiationEntity negotiation = NegotiationDtoCreate.toEntity(user, business, dto);

        persist(negotiation);

        dto.getNegotiationItems().stream().forEach(item -> negotiationItemService.create(item));

        negotiation.setQttItens(dto.getNegotiationItems().size());
        negotiation.setValue(dto.getNegotiationItems().stream().mapToDouble(NegotiationItemDtoIn::getValue).sum());

        return Response.created(UriBuilder.fromPath(Resources.NEGOTIATION).path(negotiation.getId().toString()).build()).build();
    }

    public Response update(long id, NegotiationDtoUpdate dto) {
        NegotiationEntity negotiation = findByIdOptional(id)
                .orElseThrow(() -> new WebApplicationException(
                        negotiationNotFound + id, Response.Status.NOT_FOUND));

        negotiation.setDescription(dto.getDescription());

        return Response.ok().build();
    }

    public Response disable(long id) {
        NegotiationEntity negotiation = findByIdOptional(id)
                .orElseThrow(() -> new WebApplicationException(
                        negotiationNotFound + id, Response.Status.NOT_FOUND));

        negotiation.setActive("N");

        return Response.noContent().build();
    }

}
