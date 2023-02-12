package br.com.softpethouse.api.product.dto;

import br.com.softpethouse.api.product.entity.ProductEntity;
import lombok.Builder;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.json.bind.annotation.JsonbPropertyOrder;

@Data
@Builder
@Schema(name = "Product", description = "Product dto out")
@JsonbPropertyOrder({"id", "active", "businessId", "description", "value"})
public class ProductDtoOut {

    @Schema(title = "Id Product")
    @Parameter(description = "IdProduct parameter")
    private long id;

    @Schema(title = "Ativo")
    @Parameter(description = "active parameter")
    private String active;

    @Schema(title = "Id Business")
    @Parameter(description = "IdBusiness parameter")
    private long businessId;

    @Schema(title = "Description")
    @Parameter(description = "Description parameter")
    private String description;

    @Schema(title = "Value")
    @Parameter(description = "Value parameter")
    private double value;

    @Schema(title = "Creation dateTime")
    @Parameter(description = "Creation dateTime parameter")
    private String creationDateTime;

    @Schema(title = "Update dateTime")
    @Parameter(description = "Update dateTime parameter")
    private String updateDateTime;

    public static ProductDtoOut toDto(ProductEntity entity) {
        return ProductDtoOut.builder()
                .id(entity.getId())
                .active(entity.getActive())
                .businessId(entity.getBusiness().getId())
                .description(entity.getDescription())
                .value(entity.getValue())
                .creationDateTime(entity.getCreatedAt().toString())
                .updateDateTime(entity.getUpdatedAt().toString())
                .build();
    }

}
