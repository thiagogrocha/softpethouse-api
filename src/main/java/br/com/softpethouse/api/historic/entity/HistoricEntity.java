package br.com.softpethouse.api.historic.entity;

import br.com.softpethouse.api.commom.EntityBase;
import br.com.softpethouse.api.pet.entity.PetEntity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "historical")
@SequenceGenerator(name = "HistoricalSeq", sequenceName = "seq_historical", allocationSize = 1)
public class HistoricEntity extends EntityBase {

    @Id
    @GeneratedValue(generator = "HistoricalSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private PetEntity pet;

    @Column(nullable = false)
    private String description;

}
