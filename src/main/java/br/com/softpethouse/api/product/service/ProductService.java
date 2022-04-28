package br.com.softpethouse.api.product.service;

import br.com.softpethouse.api.product.ProductEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductService implements PanacheRepository<ProductEntity> {
}
