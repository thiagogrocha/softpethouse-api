package br.com.softpethouse.api.negotiation.entity;

import br.com.softpethouse.api.business.entity.BusinessEntity;
import br.com.softpethouse.api.negotiation.dto.NegotiationDtoCreate;
import lombok.Builder;
import lombok.Data;
import br.com.softpethouse.api.commom.EntityBase;
import br.com.softpethouse.api.user.entity.UserEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    @JoinColumn(name = "iduser")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "idbusiness")
    private BusinessEntity business;

    @Column(nullable = false)
    private double value;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "idnegotiation")
    private List<NegotiationItemEntity> negotiationItems;

    @PrePersist
    private void setDefaultDateTime() {
        if (dateTime == null)
            dateTime = LocalDateTime.now();
    }

    public static NegotiationEntity toEntity(NegotiationDtoCreate dto, UserEntity user, BusinessEntity business) {
        return NegotiationEntity.builder()
                .user(user)
                .business(business)
                .value(dto.getValue())
                .description(dto.getDescription())
                .negotiationItems()
                .build();
    }

}
