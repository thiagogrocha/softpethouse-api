package br.com.softpethouse.api.business.dto;

import br.com.softpethouse.api.business.entity.BusinessEntity;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

@Data
@Schema(name = "Business", description = "Business dto")
public class BusinessDtoCreate {

    @Schema(title = "Nome do Negócio")
    @Parameter(description = "Nome parameter")
    private String name;

    @Schema(title = "Descrição")
    @Parameter(description = "Descrição parameter")
    private String description;

    public static BusinessEntity toEntity(BusinessDtoCreate dto) {
        return BusinessEntity.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }

    public static void updateEntityFromDto(BusinessDtoCreate dto, BusinessEntity entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
    }

}
