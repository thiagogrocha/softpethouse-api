package br.com.softpethouse.api.account.dto;

import br.com.softpethouse.api.account.entity.AccountEntity;
import lombok.Builder;
import lombok.Data;
import br.com.softpethouse.api.business.dto.BusinessDto;
import br.com.softpethouse.api.user.dto.UserDto;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbPropertyOrder;

@Data
@Builder
@Schema(name = "Account", description = "Account dto out")
@JsonbPropertyOrder({"id", "active", "username", "email", "user", "typeAccount", "business"})
public class AccountDtoOut {

    @Schema(title = "Id Account", implementation = Long.class)
    private long id;

    @Schema(title = "User", implementation = UserDto.class)
    private UserDto user;

    @Schema(title = "TypeAccount", implementation = TypeAccountDto.class)
    private TypeAccountDto typeAccount;

    @Schema(title = "Id Business", implementation = BusinessDto.class)
    private BusinessDto business;

    @Schema(title = "Username", implementation = String.class)
    private String username;

    @Schema(title = "E-mail", implementation = String.class)
    private String email;

    @Schema(title = "Active", implementation = String.class)
    private String active;

    public static AccountDtoOut toDto(AccountEntity entity) {
        return AccountDtoOut.builder()
                .id(entity.getId())
                .user(UserDto.toDto(entity.getUser()))
                .typeAccount(TypeAccountDto.toDto(entity.getTypeAccount()))
                .business(BusinessDto.toDto(entity.getBusiness()))
                .username(entity.getUsername())
                .email(entity.getEmail())
                .active(entity.getActive())
                .build();
    }

}
