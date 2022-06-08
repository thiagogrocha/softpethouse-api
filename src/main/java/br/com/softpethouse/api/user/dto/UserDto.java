package br.com.softpethouse.api.user.dto;

import br.com.softpethouse.api.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "User", description = "User dto in")
@JsonbPropertyOrder({"name", "age"})
public class UserDto {

    @NotBlank(message = "Campo nome é obrigatório!")
    @Schema(title = "Name")
    @Parameter(description = "Name parameter")
    private String name;

    @NotNull(message = "Campo idade é obrigatório!")
    @Schema(title = "Age")
    @Parameter(description = "Age parameter")
    private int age;

    public static UserDto fromEntity(UserEntity entity) {
        return UserDto.builder().name(entity.getName()).age(entity.getAge()).build();
    }

}
