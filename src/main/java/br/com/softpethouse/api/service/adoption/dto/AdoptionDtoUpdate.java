package br.com.softpethouse.api.service.adoption.dto;

import lombok.Builder;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@Schema(name = "Adoption", description = "Adoption dto to create")
public class AdoptionDtoUpdate {

    @NotEmpty
    @Schema(title = "New User Id", required = true)
    @Parameter(description = "userNewId parameter")
    private long userNewId;

}
