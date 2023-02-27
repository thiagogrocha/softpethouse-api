package br.com.softpethouse.api.service.negotiation.dto;

import br.com.softpethouse.api.domain.negotiation.NegotiationEntity;
import br.com.softpethouse.api.domain.negotiation.NegotiationItemEntity;
import br.com.softpethouse.api.domain.product.ProductEntity;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.validation.constraints.NotEmpty;

@Data
@Schema(name = "Item Negociação", description = "Item Negociação dto create")
public class NegotiationItemDtoIn {

    @NotEmpty(message = "Campo negotiationId é obrigatório!")
    @Schema(title = "Negotiation Id", required = true)
    @Parameter(description = "negotiationId parameter")
    private long negotiationId;

    @NotEmpty(message = "Campo productId é obrigatório!")
    @Schema(title = "ProductId Id", required = true)
    @Parameter(description = "productId parameter")
    private long productId;

    @NotEmpty(message = "Campo quantity é obrigatório!")
    @Schema(title = "Quantity", required = true)
    @Parameter(description = "quantity parameter")
    private double quantity;

    @NotEmpty(message = "Campo value é obrigatório!")
    @Schema(title = "Value", required = true)
    @Parameter(description = "value parameter")
    private double value;

    public static NegotiationItemEntity toEntity(NegotiationEntity negotiation, ProductEntity product, NegotiationItemDtoIn dto) {
        return NegotiationItemEntity.builder()
                .negotiation(negotiation)
                .product(product)
                .quantity(dto.getQuantity())
                .value(dto.getValue())
                .build();
    }

}
