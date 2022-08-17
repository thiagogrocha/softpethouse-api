package br.com.softpethouse.api.negotiation.entity;

import br.com.softpethouse.api.commom.EntityBase;
import br.com.softpethouse.api.product.entity.ProductEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name = "negotiationItems")
@SequenceGenerator(name = "NegotiationItemsSeq", sequenceName = "seq_negotiationitems", allocationSize = 1)
public class NegotiationItemEntity extends EntityBase {

    @Id
    @GeneratedValue(generator = "NegotiationItemsSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne//TODO VERIFICAR
    private ProductEntity product;

    @Column(nullable = false)
    private double qtdd;

    @Column(nullable = false)
    private double value;


}
