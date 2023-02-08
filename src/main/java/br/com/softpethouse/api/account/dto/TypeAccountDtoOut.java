package br.com.softpethouse.api.account.dto;

import lombok.Getter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.json.bind.annotation.JsonbPropertyOrder;

@Getter
@Schema(name = "Type Account", description = "Type Account dto out")
@JsonbPropertyOrder({"id", "active", "name", "description"})
public class TypeAccountDtoOut {

    @Schema(title = "Id do Tipo de Conta")
    @Parameter(description = "Id parameter")
    private long id;

    @Schema(title = "Nome do Tipo de Conta")
    @Parameter(description = "Nome parameter")
    private String name;

    @Schema(title = "Descrição")
    @Parameter(description = "Descrição parameter")
    private String description;

    @Schema(title = "Ativo")
    @Parameter(description = "Ativo parameter")
    private String active;

}
