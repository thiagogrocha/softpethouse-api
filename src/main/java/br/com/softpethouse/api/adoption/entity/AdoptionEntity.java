package br.com.softpethouse.api.adoption.entity;

import br.com.softpethouse.api.commom.EntityBase;
import br.com.softpethouse.api.pet.entity.PetEntity;
import br.com.softpethouse.api.user.entity.UserEntity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

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
    @JoinColumn(name = "pet_id")
    private PetEntity pet;

    @ManyToOne
    @JoinColumn(name = "userOl_iId")
    private UserEntity userOld;

    @ManyToOne
    @JoinColumn(name = "userNew_id")
    private UserEntity userNew;

}
