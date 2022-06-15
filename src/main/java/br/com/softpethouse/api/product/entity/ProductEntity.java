package br.com.softpethouse.api.product.entity;

import lombok.Data;
import br.com.softpethouse.api.business.entity.BusinessEntity;
import br.com.softpethouse.api.commom.EntityBase;

import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
@SequenceGenerator(name = "ProductsSeq", sequenceName = "seq_products", allocationSize = 1)
public class ProductEntity extends EntityBase {

    @Id
    @GeneratedValue(generator = "ProductsSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idbusiness")
    private BusinessEntity business;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double value;

}
