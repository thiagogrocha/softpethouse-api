package br.com.softpethouse.api.service.historic.dto;

import br.com.softpethouse.api.domain.historic.HistoricEntity;
import br.com.softpethouse.api.domain.pet.PetEntity;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.validation.constraints.NotEmpty;

@Data
@Schema(name = "Histórico", description = "Histórico dto create")
public class HistoricDtoCreate {

    @NotEmpty(message = "O Pet é obrigatório!")
    @Schema(title = "Pet", required = true)
    @Parameter(description = "Pet parameter")
    private long petId;

    @NotEmpty(message = "A Descrição é obrigatória!")
    @Schema(title = "Descrição", required = true)
    @Parameter(description = "description parameter")
    private String description;

    public static HistoricEntity toEntity(PetEntity pet, HistoricDtoCreate dto) {
        return HistoricEntity.builder()
                .pet(pet)
                .description(dto.getDescription())
                .build();
    }

}