package br.com.softpethouse.api.account.dto;

import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

@Data
@Schema(name = "Account", description = "Account dto to update")
public class AccountDtoUpdate {

    @Schema(title = "Username")
    @Parameter(description = "UserName parameter")
    private String userName;

    @Schema(title = "Password")
    @Parameter(description = "Password parameter")
    private String password;

}
