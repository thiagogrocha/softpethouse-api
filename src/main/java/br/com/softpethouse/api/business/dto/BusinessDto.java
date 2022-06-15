package br.com.softpethouse.api.business.dto;

import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.validation.constraints.NotNull;

@Data
@Schema(name = "Business", description = "Business dto in")
@JsonbPropertyOrder({"name", "description"})
public class BusinessDto {

    @NotNull(message = "O nome é obrigatório!")
    @Schema(title = "Nome do Negócio")
    @Parameter(description = "Nome parameter")
    private String name;

    @NotNull(message = "A descrição é obrigatória!")
    @Schema(title = "Descrição")
    @Parameter(description = "Descrição parameter")
    private String description;

}
