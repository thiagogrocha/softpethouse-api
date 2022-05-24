package br.com.softpethouse.api.account.dto;

import br.com.softpethouse.api.user.dto.UserDto;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Schema(name = "Account", description = "Account dto in")
public class AccountDtoIn {

    @NotNull(message = "O Usuário é obrigatório!")
    @Schema(title = "User")
    @Parameter(description = "UserDto parameter")
    private UserDto user;

    @NotNull(message = "Campo IdTypeAccount é obrigatório!")
    @Schema(title = "Id TypeAccount")
    @Parameter(description = "IdTypeAccount parameter")
    private long idTypeAccount;

    @NotNull(message = "Campo IdBusiness é obrigatório!")
    @Schema(title = "Id Business")
    @Parameter(description = "IdBusiness parameter")
    private long idBusiness;

    @NotBlank(message = "Campo nickname é obrigatório!")
    @Schema(title = "Nickname")
    @Parameter(description = "Nickname parameter")
    private String nickName;

    @NotBlank(message = "Campo email é obrigatório!")
    @Schema(title = "E-mail")
    @Parameter(description = "Email parameter")
    private String email;

    @NotBlank(message = "Campo password é obrigatório!")
    @Schema(title = "Password")
    @Parameter(description = "Password parameter")
    private String password;


}
