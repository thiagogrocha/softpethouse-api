package br.com.softpethouse.api.adoption.entity;

import br.com.softpethouse.api.adoption.dto.AdoptionDto;
import br.com.softpethouse.api.adoption.dto.AdoptionDtoCreate;
import lombok.Builder;
import lombok.Data;
import br.com.softpethouse.api.commom.EntityBase;
import br.com.softpethouse.api.pet.entity.PetEntity;
import br.com.softpethouse.api.user.entity.UserEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "adoptions")
@SequenceGenerator(name = "AdoptionsSeq", sequenceName = "seq_adoptions", allocationSize = 1)
public class AdoptionEntity extends EntityBase {

    @Id
    @GeneratedValue(generator = "AdoptionsSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idpet")
    private PetEntity pet;

    @ManyToOne
    @JoinColumn(name = "idUserOld")
    private UserEntity userOld;

    @ManyToOne
    @JoinColumn(name = "idUserNew")
    private UserEntity userNew;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @PrePersist
    private void setDefaultDateTime() {
        if (dateTime == null)
            dateTime = LocalDateTime.now();
    }

}
