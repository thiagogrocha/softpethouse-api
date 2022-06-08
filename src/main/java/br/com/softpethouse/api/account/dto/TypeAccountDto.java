package br.com.softpethouse.api.account.dto;

import br.com.softpethouse.api.account.entity.TypeAccountEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Type Account", description = "Type Account dto in")
@JsonbPropertyOrder({"name", "description"})
public class TypeAccountDto {

    @NotNull(message = "O nome é obrigatório!")
    @Schema(title = "Nome do Negócio")
    @Parameter(description = "Nome parameter")
    private String name;

    @NotNull(message = "A descrição é obrigatória!")
    @Schema(title = "Descrição")
    @Parameter(description = "Descrição parameter")
    private String description;

    public static TypeAccountDto fromEntity(TypeAccountEntity entity) {
        return TypeAccountDto.builder().name(entity.getName()).description(entity.getDescription()).build();
    }

}
