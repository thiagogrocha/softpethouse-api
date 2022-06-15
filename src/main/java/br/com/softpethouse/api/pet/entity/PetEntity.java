package br.com.softpethouse.api.pet.entity;

import lombok.Data;
import br.com.softpethouse.api.commom.EntityBase;
import br.com.softpethouse.api.user.entity.UserEntity;

import javax.persistence.*;

@Data
@Entity
@Table(name = "pets")
@SequenceGenerator(name = "PetsSeq", sequenceName = "seq_pets", allocationSize = 1)
public class PetEntity extends EntityBase {

    @Id
    @GeneratedValue(generator = "PetsSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "iduser")
    private UserEntity user;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String species;

    @Column(nullable = false)
    private String bread;

    @Column(nullable = false)
    private int age;

}
