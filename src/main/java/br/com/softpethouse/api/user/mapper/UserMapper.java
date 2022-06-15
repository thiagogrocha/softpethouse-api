package br.com.softpethouse.api.user.mapper;

import br.com.softpethouse.api.user.dto.UserDto;
import br.com.softpethouse.api.user.entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserDto toDto(UserEntity entity);

    @InheritInverseConfiguration(name = "toDto")
    UserEntity toEntity(UserDto dto);

}
