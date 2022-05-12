package br.com.softpethouse.api.account.dto;

import br.com.softpethouse.api.account.entity.BusinessEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Schema(name = "Business", description = "Business dto in")
public class BusinessDto {

    @NotNull(message = "O nome é obrigatório!")
    @Schema(title = "Nome do Negócio")
    @Parameter(description = "Nome parameter")
    private String name;

    @NotNull(message = "A descrição é obrigatória!")
    @Schema(title = "Descrição")
    @Parameter(description = "Descrição parameter")
    private String description;

    public BusinessDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static BusinessDto fromEntity(BusinessEntity entity) {
            return new BusinessDto(entity.getName(), entity.getDescription());
    }
}
