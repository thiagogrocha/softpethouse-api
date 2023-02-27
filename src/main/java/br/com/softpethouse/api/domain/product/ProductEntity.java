package br.com.softpethouse.api.domain.product;

import br.com.softpethouse.api.domain.business.BusinessEntity;
import br.com.softpethouse.api.domain.EntityBase;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "products")
@SequenceGenerator(name = "ProductsSeq", sequenceName = "seq_products", allocationSize = 1)
public class ProductEntity extends EntityBase {

    @Id
    @GeneratedValue(generator = "ProductsSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "business_id")
    private BusinessEntity business;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double value;

}
