package br.com.softpethouse.api.domain.negotiation;

import br.com.softpethouse.api.domain.EntityBase;
import br.com.softpethouse.api.domain.product.ProductEntity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "negotiationItems")
@SequenceGenerator(name = "NegotiationItemsSeq", sequenceName = "seq_negotiation_items", allocationSize = 1)
public class NegotiationItemEntity extends EntityBase {

    @Id
    @GeneratedValue(generator = "NegotiationItemsSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "negotiation_id")
    private NegotiationEntity negotiation;

    @ManyToOne//TODO VERIFICAR
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(nullable = false)
    private double quantity;

    @Column(nullable = false)
    private double value;

}
