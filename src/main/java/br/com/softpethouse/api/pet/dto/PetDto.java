package br.com.softpethouse.api.pet.dto;

import br.com.softpethouse.api.pet.entity.PetEntity;
import br.com.softpethouse.api.user.dto.UserDto;
import lombok.Builder;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@Builder
public class PetDto {

    private Long id;

    @Schema(implementation = UserDto.class)
    private UserDto user;

    private String name;

    private String species;

    private String bread;

    private int age;

    public static PetDto toDto(PetEntity entity) {
        return PetDto.builder()
                .id(entity.getId())
                .user(UserDto.toDto(entity.getUser()))
                .name(entity.getName())
                .species(entity.getSpecies())
                .bread(entity.getBread())
                .age(entity.getAge())
                .build();
    }

}
