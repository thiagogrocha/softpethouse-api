package br.com.softpethouse.api.product.dto;

import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.json.bind.annotation.JsonbPropertyOrder;

@Data
@Schema(name = "Product", description = "Product dto out")
@JsonbPropertyOrder({"id", "active", "businessId", "description", "value"})
public class ProductDtoOut {

    @Schema(title = "Id Product")
    @Parameter(description = "IdProduct parameter")
    private long id;

    @Schema(title = "Id Business")
    @Parameter(description = "IdBusiness parameter")
    private long businessId;

    @Schema(title = "Description")
    @Parameter(description = "Description parameter")
    private String description;

    @Schema(title = "Value")
    @Parameter(description = "Value parameter")
    private double value;

    @Schema(title = "Active")
    @Parameter(description = "Ativo parameter")
    private String active;

    @Schema(title = "Creation dateTime")
    @Parameter(description = "Creation dateTime parameter")
    private String creationDateTime;

    @Schema(title = "Update dateTime")
    @Parameter(description = "Update dateTime parameter")
    private String updateDateTime;

}
