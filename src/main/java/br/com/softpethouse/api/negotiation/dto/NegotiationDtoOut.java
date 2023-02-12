package br.com.softpethouse.api.negotiation.dto;

import br.com.softpethouse.api.business.dto.BusinessDtoOut;
import br.com.softpethouse.api.negotiation.entity.NegotiationEntity;
import br.com.softpethouse.api.user.dto.UserDtoOut;
import lombok.Builder;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.json.bind.annotation.JsonbPropertyOrder;
import java.time.LocalDateTime;

@Data
@Builder
@Schema(name = "Negociação", description = "Negotiation dto out")
@JsonbPropertyOrder({"id", "active", "pet", "description"})
public class NegotiationDtoOut {

    @Schema(title = "Id da Negociação")
    @Parameter(description = "Id parameter")
    private long id;

    @Schema(title = "Ativo")
    @Parameter(description = "Active parameter")
    private String active;

    @Schema(title = "User", implementation = UserDtoOut.class)
    @Parameter(description = "user parameter")
    private UserDtoOut user;

    @Schema(title = "Business", implementation = BusinessDtoOut.class)
    @Parameter(description = "business parameter")
    private BusinessDtoOut business;

    @Schema(title = "Valor")
    @Parameter(description = "value parameter")
    private double value;

    @Schema(title = "DataHora")
    @Parameter(description = "dateTime parameter")
    private LocalDateTime dateTime;

    @Schema(title = "Descrição")
    @Parameter(description = "description parameter")
    private String description;

    public static NegotiationDtoOut toDto(NegotiationEntity entity) {
        return NegotiationDtoOut.builder()
                .id(entity.getId())
                .active(entity.getActive())
                .user(UserDtoOut.toDto(entity.getUser()))
                .business(BusinessDtoOut.toDto(entity.getBusiness()))
                .build();
    }

}
