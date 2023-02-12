package br.com.softpethouse.api.negotiation.dto;

import br.com.softpethouse.api.negotiation.entity.NegotiationItemEntity;
import br.com.softpethouse.api.product.dto.ProductDtoOut;
import lombok.Builder;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.json.bind.annotation.JsonbPropertyOrder;

@Data
@Builder
@Schema(name = "Item Negociação", description = "Item Negociação dto out")
@JsonbPropertyOrder({"id", "active", "quantity", "value", "product", "negotiation"})
public class NegotiationItemDtoOut {

    @Schema(title = "Id do Item da Negociação")
    @Parameter(description = "Id parameter")
    private long id;

    @Schema(title = "Negociação", implementation = NegotiationDtoOut.class)
    @Parameter(description = "Negotiation parameter")
    private NegotiationDtoOut negotiation;

    @Schema(title = "Produto", implementation = ProductDtoOut.class)
    @Parameter(description = "Product parameter")
    private ProductDtoOut product;

    @Schema(title = "Quantidade")
    @Parameter(description = "Quantity parameter")
    private double quantity;

    @Schema(title = "Valor")
    @Parameter(description = "Value parameter")
    private double value;

    @Schema(title = "Ativo")
    @Parameter(description = "Active parameter")
    private String active;

    public static NegotiationItemDtoOut toDto(NegotiationItemEntity entity) {
        return NegotiationItemDtoOut.builder()
                .id(entity.getId())
                .negotiation(NegotiationDtoOut.toDto(entity.getNegotiation()))
                .product(ProductDtoOut.toDto(entity.getProduct()))
                .quantity(entity.getQuantity())
                .value(entity.getValue())
                .active(entity.getActive())
                .build();
    }

}
