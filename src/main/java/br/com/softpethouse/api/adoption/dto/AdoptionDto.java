package br.com.softpethouse.api.adoption.dto;

import br.com.softpethouse.api.adoption.entity.AdoptionEntity;
import br.com.softpethouse.api.pet.dto.PetDto;
import br.com.softpethouse.api.user.dto.UserDto;
import lombok.Builder;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(name = "Adoption", description = "Adoption dto")
public class AdoptionDto {

    @Schema(implementation = PetDto.class)
    private PetDto pet;

    @Schema(implementation = UserDto.class)
    private UserDto userOld;

    @Schema(implementation = UserDto.class)
    private UserDto userNew;

    @Schema(title = "Adoption DateTime")
    private LocalDateTime dateTime;

    public static AdoptionDto toDto(AdoptionEntity entity) {
        return AdoptionDto.builder()
                .pet(PetDto.toDto(entity.getPet()))
                .userOld(UserDto.toDto(entity.getUserOld()))
                .userNew(UserDto.toDto(entity.getUserNew()))
                .dateTime(entity.getDateTime())
                .build();
    }

}
