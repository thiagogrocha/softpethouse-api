package br.com.softpethouse.api.adoption.dto;

import br.com.softpethouse.api.adoption.entity.AdoptionEntity;
import lombok.Builder;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@Schema(name = "Adoption", description = "Adoption dto to create")
public class AdoptionDtoCreate {

    @NotNull(message = "Campo IdPet é obrigatório!")
    @Schema(title = "Id Pet")
    @Parameter(description = "IdPet parameter")
    private long idPet;

    @NotNull(message = "Campo IdUserOld é obrigatório!")
    @Schema(title = "Id Old User")
    @Parameter(description = "IdUserOld parameter")
    private long idUserOld;

    @NotNull(message = "Campo IdUserNew é obrigatório!")
    @Schema(title = "Id New User")
    @Parameter(description = "IdUserNew parameter")
    private long idUserNew;

    @Schema(title = "Adoption DateTime")
    @Parameter(description = "DateTime parameter")
    private LocalDateTime dateTime;

    public static AdoptionDtoCreate toDto(AdoptionEntity entity) {
        return AdoptionDtoCreate.builder()
                .idPet(entity.getPet().getId())
                .idUserOld(entity.getUserOld().getId())
                .idUserNew(entity.getUserNew().getId())
                .build();
    }

}
