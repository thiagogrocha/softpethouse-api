package br.com.softpethouse.api.negotiation.entity;

import lombok.Data;
import br.com.softpethouse.api.commom.EntityBase;
import br.com.softpethouse.api.product.entity.ProductEntity;
import br.com.softpethouse.api.user.entity.UserEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "negotiations")
@SequenceGenerator(name = "NegotiationsSeq", sequenceName = "seq_negotiations", allocationSize = 1)
public class NegotiationEntity extends EntityBase {

    @Id
    @GeneratedValue(generator = "NegotiationsSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "iduser")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "idproduct")
    private ProductEntity product;

    @Column(nullable = false)
    private double value;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @PrePersist
    private void setDefaultDateTime() {
        if (dateTime == null)
            dateTime = LocalDateTime.now();
    }

}
