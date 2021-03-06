package br.com.softpethouse.api.account.dto;

import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Schema(name = "Account", description = "Account dto to create")
public class AccountDtoUpdate {

    @NotNull(message = "Campo IdTypeAccount é obrigatório!")
    @Schema(title = "Id TypeAccount")
    @Parameter(description = "IdTypeAccount parameter")
    private long idTypeAccount;

    @NotNull(message = "Campo IdBusiness é obrigatório!")
    @Schema(title = "Id Business")
    @Parameter(description = "IdBusiness parameter")
    private long idBusiness;

    @NotBlank(message = "Campo Nome de Usuário é obrigatório!")
    @Schema(title = "Username")
    @Parameter(description = "Username parameter")
    private String userName;

    @NotBlank(message = "Campo password é obrigatório!")
    @Schema(title = "Password")
    @Parameter(description = "Password parameter")
    private String password;

}
