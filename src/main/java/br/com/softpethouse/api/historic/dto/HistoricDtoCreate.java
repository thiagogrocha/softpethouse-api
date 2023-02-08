package br.com.softpethouse.api.historic.dto;

import lombok.Getter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.validation.constraints.NotNull;

@Getter
@Schema(name = "Histórico", description = "Histórico dto create")
public class HistoricDtoCreate {

    @NotNull(message = "O Pet é obrigatório!")
    @Schema(title = "Pet", implementation = Long.class, required = true)
    @Parameter(description = "Pet parameter")
    private long petId;

    @Schema(title = "Descrição", implementation = String.class, required = true)
    @Parameter(description = "Descrição parameter")
    private String description;

}