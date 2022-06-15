package br.com.softpethouse.api.account.mapper;

import br.com.softpethouse.api.account.dto.AccountDtoCreate;
import br.com.softpethouse.api.account.dto.AccountDtoOut;
import br.com.softpethouse.api.account.dto.AccountDtoUpdate;
import br.com.softpethouse.api.account.entity.AccountEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface AccountMapper {
    List<AccountDtoOut> toDtoList(List<AccountEntity> entities);

    AccountDtoOut toDto(AccountEntity entity);

    @InheritInverseConfiguration(name = "toDto")
    AccountEntity toEntity(AccountDtoCreate dto);

    void updateEntityFromDto(AccountDtoUpdate dto, @MappingTarget AccountEntity entity);

    void updateDtoFromEntity(AccountEntity entity, @MappingTarget AccountDtoOut dto);

}
