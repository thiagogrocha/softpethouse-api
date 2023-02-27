package br.com.softpethouse.api.service.account.dto;

import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.validation.constraints.NotEmpty;

@Data
@Schema(name = "Account", description = "Account dto to update")
public class AccountDtoUpdate {

    @NotEmpty(message = "Campo username é obrigatório!")
    @Schema(title = "Username", required = true)
    @Parameter(description = "Username parameter")
    private String username;

    @NotEmpty(message = "Campo password é obrigatório!")
    @Schema(title = "Password", required = true)
    @Parameter(description = "Password parameter")
    private String password;

}
