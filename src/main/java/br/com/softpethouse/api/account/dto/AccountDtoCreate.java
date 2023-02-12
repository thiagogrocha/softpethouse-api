package br.com.softpethouse.api.account.dto;

import br.com.softpethouse.api.user.dto.UserDtoCreate;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@Schema(name = "Account", description = "Account dto to create")
public class AccountDtoCreate {

    @NotEmpty(message = "O Usuário é obrigatório!")
    @Schema(title = "User", required = true, implementation = UserDtoCreate.class)
    @Parameter(description = "UserDtoCreate parameter")
    private UserDtoCreate user;

    @NotEmpty(message = "Campo typeAccountId é obrigatório!")
    @Schema(title = "TypeAccount Id", required = true)
    @Parameter(description = "typeAccountId parameter")
    private long typeAccountId;

    @NotEmpty(message = "Campo BusinessId é obrigatório!")
    @Schema(title = "Business Id", required = true)
    @Parameter(description = "BusinessId parameter")
    private long businessId;

    @NotEmpty(message = "Campo Nome de Usuário é obrigatório!")
    @Schema(title = "Username", required = true)
    @Parameter(description = "Username parameter")
    private String username;

    @NotEmpty(message = "Campo email é obrigatório!")
    @Schema(title = "E-mail", required = true)
    @Parameter(description = "Email parameter")
    @Email(regexp = ".+@.+\\..+", message = "Insira um e-mail válido!")
    private String email;

    @NotEmpty(message = "Campo password é obrigatório!")
    @Schema(title = "Password", required = true)
    @Parameter(description = "Password parameter")
    private String password;

}
