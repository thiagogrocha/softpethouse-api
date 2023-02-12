package br.com.softpethouse.api.user.dto;

import br.com.softpethouse.api.user.entity.UserEntity;
import lombok.Builder;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@Builder
@Schema(name = "User", description = "User dto Create")
@JsonbPropertyOrder({"name", "age"})
public class UserDtoCreate {

    @NotEmpty(message = "Campo name é obrigatório!")
    @Schema(title = "Name", required = true)
    @Parameter(description = "Name parameter")
    private String name;

    @NotEmpty(message = "Campo dtBirth é obrigatório!")
    @Schema(title = "DtBirth", required = true)
    @Parameter(description = "DtBirth parameter")
    private LocalDate dtBirth;

    public static UserEntity toEntity(UserDtoCreate dto) {
        return UserEntity.builder()
                .name(dto.getName())
                .dtBirth(dto.getDtBirth())
                .build();
    }

}
