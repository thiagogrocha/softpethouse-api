package br.com.softpethouse.api.domain.pet;

import lombok.Data;
import br.com.softpethouse.api.domain.EntityBase;
import br.com.softpethouse.api.domain.user.UserEntity;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "pets")
@SequenceGenerator(name = "PetsSeq", sequenceName = "seq_pets", allocationSize = 1)
public class PetEntity extends EntityBase {

    @Id
    @GeneratedValue(generator = "PetsSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String species;

    @Column(nullable = false)
    private String bread;

    @Column(nullable = false)
    private LocalDate dtBirth;

}
