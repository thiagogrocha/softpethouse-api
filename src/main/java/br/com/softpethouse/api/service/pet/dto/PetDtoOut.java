package br.com.softpethouse.api.service.pet.dto;

import br.com.softpethouse.api.domain.pet.PetEntity;
import br.com.softpethouse.api.service.user.dto.UserDtoOut;
import lombok.Builder;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbPropertyOrder;

@Data
@Builder
@Schema(name = "Pet", description = "Pet dto out")
@JsonbPropertyOrder({"id", "active", "name", "email", "user", "typeAccount", "business"})
public class PetDtoOut {

    @Schema(title = "Account Id")
    private long id;

    @Schema(implementation = UserDtoOut.class)
    private UserDtoOut user;

    private String name;

    private String species;

    private String bread;

    private String dtBirth;

    @Schema(title = "Active")
    private String active;

    public static PetDtoOut toDto(PetEntity entity) {
        return PetDtoOut.builder()
                .id(entity.getId())
                .user(UserDtoOut.toDto(entity.getUser()))
                .name(entity.getName())
                .species(entity.getSpecies())
                .bread(entity.getBread())
                .dtBirth(entity.getDtBirth().toString())
                .build();
    }

}
