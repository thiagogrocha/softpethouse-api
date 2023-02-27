package br.com.softpethouse.api.domain.adoption;

import br.com.softpethouse.api.domain.EntityBase;
import br.com.softpethouse.api.domain.pet.PetEntity;
import br.com.softpethouse.api.domain.user.UserEntity;
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
    @Column(updatable = false)
    private PetEntity pet;

    @ManyToOne
    @JoinColumn(name = "userold_id")
    @Column(updatable = false)
    private UserEntity userOld;

    @ManyToOne
    @JoinColumn(name = "usernew_id")
    private UserEntity userNew;

}
