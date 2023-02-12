package br.com.softpethouse.api.adoption.dto;

import br.com.softpethouse.api.adoption.entity.AdoptionEntity;
import br.com.softpethouse.api.pet.dto.PetDtoOut;
import br.com.softpethouse.api.user.dto.UserDtoOut;
import lombok.Builder;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(name = "Adoption", description = "Adoption dto")
public class AdoptionDtoOut {

    @Schema(title = "Adoption Id")
    private long id;

    @Schema(implementation = PetDtoOut.class)
    private PetDtoOut pet;

    @Schema(implementation = UserDtoOut.class)
    private UserDtoOut userOld;

    @Schema(implementation = UserDtoOut.class)
    private UserDtoOut userNew;

    @Schema(title = "Adoption DateTime")
    private LocalDateTime dateTime;

    @Schema(title = "Active")
    private String active;

    public static AdoptionDtoOut toDto(AdoptionEntity entity) {
        return AdoptionDtoOut.builder()
                .id(entity.getId())
                .pet(PetDtoOut.toDto(entity.getPet()))
                .userOld(UserDtoOut.toDto(entity.getUserOld()))
                .userNew(UserDtoOut.toDto(entity.getUserNew()))
                .dateTime(entity.getDateTime())
                .active(entity.getActive())
                .build();
    }

}
