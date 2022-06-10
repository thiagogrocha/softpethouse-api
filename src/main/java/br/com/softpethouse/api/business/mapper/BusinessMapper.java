package br.com.softpethouse.api.business.mapper;

import br.com.softpethouse.api.business.dto.BusinessDto;
import br.com.softpethouse.api.business.dto.BusinessDtoOut;
import br.com.softpethouse.api.business.entity.BusinessEntity;
import br.com.softpethouse.api.commom.MapStructConfig;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(config = MapStructConfig.class)
public interface BusinessMapper {

    List<BusinessDtoOut> toDtoList(List<BusinessEntity> entities);

    BusinessDtoOut toDto(BusinessEntity entity);

    @InheritInverseConfiguration(name = "toDto")
    BusinessEntity toEntity(BusinessDto dto);

    void updateEntityFromDto(BusinessDto dto, @MappingTarget BusinessEntity entity);

    void updateDtoFromEntity(BusinessEntity entity, @MappingTarget BusinessDtoOut dto);

}
