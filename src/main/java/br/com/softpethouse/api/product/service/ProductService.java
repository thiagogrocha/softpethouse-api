package br.com.softpethouse.api.product.service;

import lombok.extern.slf4j.Slf4j;
import br.com.softpethouse.api.Resources;
import br.com.softpethouse.api.product.entity.ProductEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Slf4j
@Transactional
@RequestScoped
public class ProductService implements PanacheRepository<ProductEntity> {

    public Response products() {
        return Response.ok(findAll().list()).build();
    }

    public Response products(long id) {
        ProductEntity entity = findById(id);

        if (entity == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(entity).build();
    }

    @Transactional
    public Response create(ProductEntity product) {

        persist(product);

        return Response.created(UriBuilder.fromPath(Resources.BUSINESS).path(product.getId().toString()).build()).build();
    }

    @Transactional
    public Response update(long id, ProductEntity product) {
        ProductEntity entity = findById(id);

        if (entity == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        entity.setBusiness(product.getBusiness());
        entity.setDescription(product.getDescription());
        entity.setValue(product.getValue());

        return Response.ok().build();
    }

    @Transactional
    public Response disable(long id) {
        ProductEntity entity = findById(id);

        if (entity == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        entity.setActive("N");

        return Response.noContent().build();
    }

}
