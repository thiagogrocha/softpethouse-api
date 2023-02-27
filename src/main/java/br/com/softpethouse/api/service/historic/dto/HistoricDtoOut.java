package br.com.softpethouse.api.service.historic.dto;

import br.com.softpethouse.api.domain.historic.HistoricEntity;
import br.com.softpethouse.api.service.pet.dto.PetDtoOut;
import lombok.Builder;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.json.bind.annotation.JsonbPropertyOrder;

@Data
@Builder
@Schema(name = "Histórico", description = "Business dto out")
@JsonbPropertyOrder({"id", "active", "pet", "description"})
public class HistoricDtoOut {

    @Schema(title = "Id do Histórico")
    @Parameter(description = "Id parameter")
    private long id;

    @Schema(title = "Ativo")
    @Parameter(description = "active parameter")
    private String active;

    @Schema(title = "Pet", implementation = PetDtoOut.class)
    @Parameter(description = "pet parameter")
    private PetDtoOut pet;

    @Schema(title = "Descrição")
    @Parameter(description = "description parameter")
    private String description;

    @Schema(title = "Data e hora de criação")
    @Parameter(description = "dateTime parameter")
    private String dateTime;

    public static HistoricDtoOut toDto(HistoricEntity entity) {
        return HistoricDtoOut.builder()
                .id(entity.getId())
                .pet(PetDtoOut.toDto(entity.getPet()))
                .description(entity.getDescription())
                .active(entity.getActive())
                .dateTime(entity.getActive())
                .build();
    }

}