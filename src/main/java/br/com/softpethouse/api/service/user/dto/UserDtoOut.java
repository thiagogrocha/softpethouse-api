package br.com.softpethouse.api.service.user.dto;

import br.com.softpethouse.api.domain.user.UserEntity;
import lombok.Builder;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.json.bind.annotation.JsonbPropertyOrder;
import java.time.LocalDate;

@Data
@Builder
@Schema(name = "User", description = "User dto in")
@JsonbPropertyOrder({"name", "age"})
public class UserDtoOut {

    @Schema(title = "Name")
    @Parameter(description = "Name parameter")
    private String name;

    @Schema(title = "Age")
    @Parameter(description = "Age parameter")
    private LocalDate dtBirth;

    public static UserDtoOut toDto(UserEntity entity) {
        return UserDtoOut.builder()
                .name(entity.getName())
                .dtBirth(entity.getDtBirth())
                .build();
    }

}
