package br.com.softpethouse.api.service.product.dto;

import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.validation.constraints.NotEmpty;

@Data
@Schema(name = "Product", description = "Product dto to update")
public class ProductDtoUpdate {

    @NotEmpty(message = "A descrição é obrigatória!")
    @Schema(title = "Description", required = true)
    @Parameter(description = "Description parameter")
    private String description;

    @NotEmpty(message = "O valor é obrigatório!")
    @Schema(title = "Value", required = true)
    @Parameter(description = "Value parameter")
    private double value;

}
