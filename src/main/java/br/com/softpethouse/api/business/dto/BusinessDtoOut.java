package br.com.softpethouse.api.business.dto;

import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.json.bind.annotation.JsonbPropertyOrder;

@Data
@Schema(name = "Business", description = "Business dto out")
@JsonbPropertyOrder({"id", "active", "name", "description"})
public class BusinessDtoOut {

    @Schema(title = "Id do Negócio")
    @Parameter(description = "Id parameter")
    private long id;

    @Schema(title = "Nome do Negócio")
    @Parameter(description = "Nome parameter")
    private String name;

    @Schema(title = "Descrição")
    @Parameter(description = "Descrição parameter")
    private String description;

    @Schema(title = "Ativo")
    @Parameter(description = "Ativo parameter")
    private String active;

}
