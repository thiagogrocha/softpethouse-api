package br.com.softpethouse.api.account.dto;

import br.com.softpethouse.api.user.dto.UserDto;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Schema(name = "Account", description = "Account dto to create")
public class AccountDtoCreate {

    @NotEmpty(message = "O Usuário é obrigatório!")
    @Schema(title = "User", required = true)
    @Parameter(description = "UserDto parameter")
    private UserDto user;

    @NotEmpty(message = "Campo IdTypeAccount é obrigatório!")
    @Schema(title = "Id TypeAccount", required = true)
    @Parameter(description = "IdTypeAccount parameter")
    private long typeAccountId;

    @NotEmpty(message = "Campo IdBusiness é obrigatório!")
    @Schema(title = "Id Business", required = true)
    @Parameter(description = "IdBusiness parameter")
    private long businessId;

    @NotBlank(message = "Campo Nome de Usuário é obrigatório!")
    @Schema(title = "Username", required = true)
    @Parameter(description = "Username parameter")
    private String username;

    @NotBlank(message = "Campo email é obrigatório!")
    @Schema(title = "E-mail", required = true)
    @Parameter(description = "Email parameter")
    @Email(regexp = ".+@.+\\..+", message = "Insira um e-mail válido!")
    private String email;

    @NotBlank(message = "Campo password é obrigatório!")
    @Schema(title = "Password", required = true)
    @Parameter(description = "Password parameter")
    private String password;

}
