package br.com.softpethouse.api.service.negotiation;

import br.com.softpethouse.api.domain.negotiation.NegotiationEntity;
import br.com.softpethouse.api.domain.negotiation.NegotiationItemEntity;
import br.com.softpethouse.api.domain.product.ProductEntity;
import br.com.softpethouse.api.service.product.ProductService;
import br.com.softpethouse.api.service.negotiation.dto.NegotiationItemDtoIn;
import br.com.softpethouse.api.service.negotiation.dto.NegotiationItemDtoOut;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@RequestScoped
public class NegotiationItemService implements PanacheRepository<NegotiationItemEntity> {

    @Inject
    NegotiationService negotiationService;

    @Inject
    ProductService productService;

    private String productNotFound = "Produto não encontrado! Id: ";

    private String negotiationNotFound = "Negociação não encontrado! Id: ";

    private String negotiationItemNotFound = "Item negociação não encontrado! Id: ";

    public Response negotiationItems(long negotiationId) {
        NegotiationEntity negotiation = negotiationService.findByIdOptional(negotiationId)
                .orElseThrow(() -> new WebApplicationException(
                        negotiationItemNotFound + negotiationId, Response.Status.NOT_FOUND));

        return Response.ok(list("idnegotiation", negotiation.getId())
                .stream().map(NegotiationItemDtoOut::toDto).collect(Collectors.toList())).build();
    }

    public NegotiationItemEntity create(NegotiationItemDtoIn dto) {
        NegotiationEntity negotiation = negotiationService.findByIdOptional(dto.getNegotiationId())
                .orElseThrow(() -> new WebApplicationException(
                        negotiationNotFound + dto.getNegotiationId(), Response.Status.NOT_FOUND));

        ProductEntity product = productService.findByIdOptional(dto.getProductId())
                .orElseThrow(() -> new WebApplicationException(
                        productNotFound + dto.getProductId(), Response.Status.NOT_FOUND));

        NegotiationItemEntity negotiationItem = NegotiationItemDtoIn.toEntity(negotiation, product, dto);

        persist(negotiationItem);

        return negotiationItem;
    }

    public Response disable(long id) {
        NegotiationItemEntity negotiationItem = findByIdOptional(id)
                .orElseThrow(() -> new WebApplicationException(
                        negotiationItemNotFound + id, Response.Status.NOT_FOUND));

        negotiationItem.setActive("N");

        return Response.noContent().build();
    }

}
