package br.com.softpethouse.api.historic.dto;

import br.com.softpethouse.api.pet.dto.PetDto;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.json.bind.annotation.JsonbPropertyOrder;

@Data
@Schema(name = "Histórico", description = "Business dto out")
@JsonbPropertyOrder({"id", "active", "pet", "description"})
public class HistoricDtoOut {

    @Schema(title = "Id do Histórico")
    @Parameter(description = "Id parameter")
    private long id;

    @Schema(title = "Pet", implementation = PetDto.class)
    @Parameter(description = "Pet parameter")
    private PetDto pet;

    @Schema(title = "Descrição")
    @Parameter(description = "Descrição parameter")
    private String description;

    @Schema(title = "Ativo")
    @Parameter(description = "Ativo parameter")
    private String active;

    @Schema(title = "Data e hora de criação")
    @Parameter(description = "DataHora parameter")
    private String dateTime;

}