package br.com.softpethouse.api.service.account.dto;

import br.com.softpethouse.api.domain.account.AccountEntity;
import br.com.softpethouse.api.service.business.dto.BusinessDtoOut;
import br.com.softpethouse.api.service.user.dto.UserDtoOut;
import lombok.Builder;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbPropertyOrder;

@Data
@Builder
@Schema(name = "Account", description = "Account dto out")
@JsonbPropertyOrder({"id", "active", "username", "email", "user", "typeAccount", "business"})
public class AccountDtoOut {

    @Schema(title = "Account Id")
    private long id;

    @Schema(title = "User", implementation = UserDtoOut.class)
    private UserDtoOut user;

    @Schema(title = "TypeAccount", implementation = TypeAccountDtoOut.class)
    private TypeAccountDtoOut typeAccount;

    @Schema(title = "Business", implementation = BusinessDtoOut.class)
    private BusinessDtoOut business;

    @Schema(title = "Username")
    private String username;

    @Schema(title = "E-mail")
    private String email;

    @Schema(title = "Active")
    private String active;

    public static AccountDtoOut toDto(AccountEntity entity) {
        return AccountDtoOut.builder()
                .id(entity.getId())
                .user(UserDtoOut.toDto(entity.getUser()))
                .typeAccount(TypeAccountDtoOut.toDto(entity.getTypeAccount()))
                .business(BusinessDtoOut.toDto(entity.getBusiness()))
                .username(entity.getUsername())
                .email(entity.getEmail())
                .active(entity.getActive())
                .build();
    }

}
