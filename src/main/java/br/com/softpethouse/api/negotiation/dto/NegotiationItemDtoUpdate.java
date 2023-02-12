package br.com.softpethouse.api.negotiation.dto;

import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.validation.constraints.NotEmpty;

@Data
@Schema(name = "Item Negociação", description = "Item Negociação dto create")
public class NegotiationItemDtoUpdate {

    @NotEmpty(message = "Campo quantity é obrigatório!")
    @Schema(title = "Quantity", required = true)
    @Parameter(description = "quantity parameter")
    private double quantity;

    @NotEmpty(message = "Campo value é obrigatório!")
    @Schema(title = "Value", required = true)
    @Parameter(description = "value parameter")
    private double value;

}
