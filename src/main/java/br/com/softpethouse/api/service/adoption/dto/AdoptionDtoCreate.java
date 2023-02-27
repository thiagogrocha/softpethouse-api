package br.com.softpethouse.api.service.adoption.dto;

import br.com.softpethouse.api.domain.adoption.AdoptionEntity;
import br.com.softpethouse.api.domain.pet.PetEntity;
import br.com.softpethouse.api.domain.user.UserEntity;
import lombok.Builder;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.validation.constraints.NotEmpty;
import java.util.Optional;

@Data
@Builder
@Schema(name = "Adoption", description = "Adoption dto to create")
public class AdoptionDtoCreate {

    @NotEmpty(message = "Campo petId é obrigatório!")
    @Schema(title = "Pet Id", required = true)
    @Parameter(description = "petId parameter")
    private long petId;

    @Schema(title = "New User Id")
    @Parameter(description = "userNewId parameter")
    private Optional<Long> userNewId;

    public static AdoptionEntity toEntity(PetEntity pet, UserEntity userNew) {
        return AdoptionEntity.builder()
                .pet(pet)
                .userOld(pet.getUser())
                .userNew(userNew)
                .build();
    }

}
