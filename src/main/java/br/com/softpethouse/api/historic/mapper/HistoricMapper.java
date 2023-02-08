package br.com.softpethouse.api.historic.mapper;

import br.com.softpethouse.api.historic.dto.HistoricDtoOut;
import br.com.softpethouse.api.historic.entity.HistoricEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface HistoricMapper {

    List<HistoricDtoOut> toDtoList(List<HistoricEntity> list);

}
