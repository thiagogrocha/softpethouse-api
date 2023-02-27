package br.com.softpethouse.api.service.product;

import br.com.softpethouse.api.infra.resource.Resources;
import br.com.softpethouse.api.domain.business.BusinessEntity;
import br.com.softpethouse.api.service.business.BusinessService;
import br.com.softpethouse.api.service.product.dto.ProductDtoCreate;
import br.com.softpethouse.api.service.product.dto.ProductDtoOut;
import br.com.softpethouse.api.service.product.dto.ProductDtoUpdate;
import br.com.softpethouse.api.domain.product.ProductEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Slf4j
@Transactional
@RequestScoped
public class ProductService implements PanacheRepository<ProductEntity> {

    @Inject
    BusinessService businessService;

    private String businessNotFound = "Negócio não encontrado! Id: ";

    private String productNotFound = "Produto não encontrado! Id: ";

    public Response products() {
        return Response.ok(findAll().list()).build();
    }

    public Response productsByBusiness(long businessId) {
        BusinessEntity business = businessService.findByIdOptional(businessId)
                .orElseThrow(() -> new WebApplicationException(
                        businessNotFound + businessId, Response.Status.NOT_FOUND));

        return Response.ok(list("businessid", business.getId()).stream().map(ProductDtoOut::toDto)).build();
    }

    public Response create(ProductDtoCreate dto) {
        BusinessEntity business = businessService.findByIdOptional(dto.getBusinessId())
                .orElseThrow(() -> new WebApplicationException(
                        businessNotFound + dto.getBusinessId(), Response.Status.NOT_FOUND));

        ProductEntity entity = ProductEntity.builder().
                business(business).
                description(dto.getDescription()).
                value(dto.getValue()).build();

        persist(entity);

        return Response.created(UriBuilder.fromPath(Resources.BUSINESS).path(entity.getId().toString()).build()).build();
    }

    public Response update(long id, ProductDtoUpdate dto) {
        ProductEntity entity = findByIdOptional(id)
                .orElseThrow(() -> new WebApplicationException(
                        productNotFound + id, Response.Status.NOT_FOUND));

        entity.setDescription(dto.getDescription());
        entity.setValue(dto.getValue());

        return Response.ok().build();
    }

    public Response disable(long id) {
        ProductEntity entity = findByIdOptional(id)
                .orElseThrow(() -> new WebApplicationException(
                        productNotFound + id, Response.Status.NOT_FOUND));

        entity.setActive("N");

        return Response.noContent().build();
    }

}
