package br.com.softpethouse.api.user.dto;

import br.com.softpethouse.api.user.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Schema(name = "User", description = "User dto in")
public class UserDto {

    @NotBlank(message = "Campo nome é obrigatório!")
    @Schema(title = "Name")
    @Parameter(description = "Name parameter")
    private String name;

    @NotNull(message = "Campo idade é obrigatório!")
    @Schema(title = "Age")
    @Parameter(description = "Age parameter")
    private int age;

    public UserDto(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static UserDto fromEntity(UserEntity entity) {
    return new UserDto(entity.getName(), entity.getAge());
    }
}
