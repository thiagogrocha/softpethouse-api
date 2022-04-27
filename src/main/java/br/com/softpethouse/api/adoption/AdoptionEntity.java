package br.com.softpethouse.api.adoption;

import br.com.softpethouse.api.commom.EntityBase;
import br.com.softpethouse.api.pet.PetEntity;
import br.com.softpethouse.api.user.UserEntity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
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
    public void prePersist() {
        if (dateTime == null)
            dateTime = LocalDateTime.now();
    }

}
