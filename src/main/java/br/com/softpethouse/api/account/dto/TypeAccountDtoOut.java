package br.com.softpethouse.api.account.dto;

import br.com.softpethouse.api.account.entity.TypeAccountEntity;
import lombok.Builder;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.json.bind.annotation.JsonbPropertyOrder;

@Data
@Builder
@Schema(name = "Type Account", description = "Type Account dto out")
@JsonbPropertyOrder({"id", "active", "name", "description"})
public class TypeAccountDtoOut {

    @Schema(title = "Id do Tipo de Conta")
    @Parameter(description = "id parameter")
    private long id;

    @Schema(title = "Name")
    @Parameter(description = "name parameter")
    private String name;

    @Schema(title = "Description")
    @Parameter(description = "description parameter")
    private String description;

    @Schema(title = "Active")
    @Parameter(description = "active parameter")
    private String active;

    public static TypeAccountDtoOut toDto(TypeAccountEntity entity) {
        return TypeAccountDtoOut.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .active(entity.getActive())
                .build();
    }

}
