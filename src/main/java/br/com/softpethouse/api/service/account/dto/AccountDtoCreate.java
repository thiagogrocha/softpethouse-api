package br.com.softpethouse.api.service.account.dto;

import br.com.softpethouse.api.domain.account.AccountEntity;
import br.com.softpethouse.api.domain.account.TypeAccountEntity;
import br.com.softpethouse.api.domain.business.BusinessEntity;
import br.com.softpethouse.api.service.user.dto.UserDtoCreate;
import br.com.softpethouse.api.domain.user.UserEntity;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Optional;

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

    @Schema(title = "Business Id")
    @Parameter(description = "BusinessId parameter")
    private Optional<Long> businessId;

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

    public static AccountEntity toEntity(AccountDtoCreate dto, TypeAccountEntity typeAccount, BusinessEntity business, UserEntity user) {
        return AccountEntity.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .typeAccount(typeAccount)
                .business(business)
                .user(user)
                .build();
    }

}
