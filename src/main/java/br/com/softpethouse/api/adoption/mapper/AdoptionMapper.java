package br.com.softpethouse.api.adoption.mapper;

import br.com.softpethouse.api.adoption.dto.AdoptionDto;
import br.com.softpethouse.api.adoption.entity.AdoptionEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface AdoptionMapper {
    List<AdoptionDto> toDtoList(List<AdoptionEntity> entities);

    AdoptionDto toDto(AdoptionEntity entity);

    @InheritInverseConfiguration(name = "toDto")
    AdoptionEntity toEntity(AdoptionDto dto);

    void updateEntityFromDto(AdoptionDto dto, @MappingTarget AdoptionEntity entity);

    void updateDtoFromEntity(AdoptionEntity entity, @MappingTarget AdoptionDto dto);
    
}
