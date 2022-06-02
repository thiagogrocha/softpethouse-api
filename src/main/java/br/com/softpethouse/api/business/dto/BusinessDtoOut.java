package br.com.softpethouse.api.business.dto;

import br.com.softpethouse.api.business.entity.BusinessEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

@Data
@NoArgsConstructor
@Schema(name = "Business", description = "Business dto out")
@JsonPropertyOrder({"id", "name", "description", "active"})
public class BusinessDtoOut {

    @Schema(title = "Id do Negócio")
    @Parameter(description = "Id parameter")
    @JsonProperty(index = 1)
    private long id;

    @Schema(title = "Nome do Negócio")
    @Parameter(description = "Nome parameter")
    @JsonProperty(index = 2)
    private String name;

    @Schema(title = "Descrição")
    @Parameter(description = "Descrição parameter")
    @JsonProperty(index = 3)
    private String description;

    @Schema(title = "Ativo")
    @Parameter(description = "Ativo parameter")
    @JsonProperty(index = 4)
    private String active;

    public BusinessDtoOut(long id, String name, String description, String active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
    }

    public static BusinessDtoOut fromEntity(BusinessEntity entity) {
        return new BusinessDtoOut(entity.getId(), entity.getName(), entity.getDescription(), entity.getActive());
    }
}
