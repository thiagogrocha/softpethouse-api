package br.com.softpethouse.api.service.account.dto;

import br.com.softpethouse.api.domain.account.TypeAccountEntity;
import lombok.Builder;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@Schema(name = "Type Account", description = "Type Account dto in")
@JsonbPropertyOrder({"name", "description"})
public class TypeAccountDtoCreate {

    @NotEmpty(message = "O campo name é obrigatório!")
    @Schema(title = "Name", required = true)
    @Parameter(description = "Name parameter")
    private String name;

    @NotEmpty(message = "O campo description é obrigatória!")
    @Schema(title = "Description", required = true)
    @Parameter(description = "description parameter")
    private String description;

    public static TypeAccountEntity toEntity(TypeAccountDtoCreate dto) {
        return TypeAccountEntity.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }

}
