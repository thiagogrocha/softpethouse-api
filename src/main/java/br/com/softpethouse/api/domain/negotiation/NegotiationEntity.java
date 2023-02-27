package br.com.softpethouse.api.domain.negotiation;

import br.com.softpethouse.api.domain.business.BusinessEntity;
import br.com.softpethouse.api.domain.EntityBase;
import br.com.softpethouse.api.domain.user.UserEntity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "negotiations")
@SequenceGenerator(name = "NegotiationsSeq", sequenceName = "seq_negotiations", allocationSize = 1)
public class NegotiationEntity extends EntityBase {

    @Id
    @GeneratedValue(generator = "NegotiationsSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "business_id")
    private BusinessEntity business;

    @Column(nullable = false)
    private double value;

    @Column(nullable = false)
    private int qttItens;

    @Column(nullable = false)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "negotiation_id")
    private List<NegotiationItemEntity> negotiationItems;

}
