package br.com.softpethouse.api.service.negotiation.dto;

import br.com.softpethouse.api.domain.business.BusinessEntity;
import br.com.softpethouse.api.domain.negotiation.NegotiationEntity;
import br.com.softpethouse.api.domain.user.UserEntity;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Schema(name = "Negociação", description = "Negociação dto create")
public class NegotiationDtoCreate {

    @NotEmpty(message = "Campo userId é obrigatório!")
    @Schema(title = "UserId Id", required = true)
    @Parameter(description = "userId parameter")
    private long userId;

    @NotEmpty(message = "Campo businessId é obrigatório!")
    @Schema(title = "BusinessId Id", required = true)
    @Parameter(description = "businessId parameter")
    private long businessId;

    @NotEmpty(message = "Campo description é obrigatório!")
    @Schema(title = "Description", required = true)
    @Parameter(description = "description parameter")
    private String description;

    @NotEmpty(message = "Campo negotiationItems é obrigatório!")
    @Schema(title = "NegotiationItems", required = true, implementation = NegotiationItemDtoIn[].class)
    @Parameter(description = "negotiationItems parameter")
    private List<NegotiationItemDtoIn> negotiationItems;

    public static NegotiationEntity toEntity(UserEntity user, BusinessEntity business, NegotiationDtoCreate dto) {
        return NegotiationEntity.builder()
                .user(user)
                .business(business)
                .description(dto.getDescription())
                .build();
    }

}
