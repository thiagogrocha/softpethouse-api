package br.com.softpethouse.api.account.dto;

import br.com.softpethouse.api.user.dto.UserDto;
import lombok.Data;

@Data
public class AccountDtoOut {

    private UserDto user;

    private TypeAccountDto typeAccount;

    private BusinessDto business;

    private String nickname;

    private String email;

}
