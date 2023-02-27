package br.com.softpethouse.api.service.business.dto;

import br.com.softpethouse.api.domain.business.BusinessEntity;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

@Data
@Schema(name = "Business", description = "Business dto create")
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

}
