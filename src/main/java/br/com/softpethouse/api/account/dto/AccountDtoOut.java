package br.com.softpethouse.api.account.dto;

import br.com.softpethouse.api.account.entity.AccountEntity;
import br.com.softpethouse.api.user.dto.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;


@Data
@NoArgsConstructor
@Schema(name = "Account", description = "Account dto out")
public class AccountDtoOut {

    @Schema(title = "User")
    @Parameter(description = "User")
    private UserDto user;

    @Schema(title = "Id TypeAccount")
    @Parameter(description = "IdTypeAccount")
    private TypeAccountDto typeAccount;

    @Schema(title = "Id Business")
    @Parameter(description = "IdBusiness")
    private BusinessDto business;

    @Schema(title = "Username")
    @Parameter(description = "Username")
    private String username;

    @Schema(title = "E-mail")
    @Parameter(description = "Email")
    private String email;

    public AccountDtoOut(UserDto user, TypeAccountDto typeAccount, BusinessDto business, String username, String email) {
        this.user = user;
        this.typeAccount = typeAccount;
        this.business = business;
        this.username = username;
        this.email = email;
    }

    public static AccountDtoOut fromEntity(AccountEntity entity){
        return new AccountDtoOut(UserDto.fromEntity(entity.getUser()), TypeAccountDto.fromEntity(entity.getTypeAccount()), BusinessDto.fromEntity(entity.getBusiness()), entity.getUsername(), entity.getPassword());
    }

}
