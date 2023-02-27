package br.com.softpethouse.api.service.negotiation.dto;

import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.validation.constraints.NotEmpty;

@Data
@Schema(name = "Negociação", description = "Negociação dto update")
public class NegotiationDtoUpdate {

    @NotEmpty(message = "Campo description é obrigatório!")
    @Schema(title = "Description", required = true)
    @Parameter(description = "description parameter")
    private String description;

}
