package br.com.softpethouse.api.product.mapper;

import br.com.softpethouse.api.product.dto.ProductDtoOut;
import br.com.softpethouse.api.product.entity.ProductEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface ProductMapper {

    List<ProductDtoOut> toDtoList(List<ProductEntity> entityList);

}
