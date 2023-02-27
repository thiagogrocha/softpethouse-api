package br.com.softpethouse.api.domain.historic;

import br.com.softpethouse.api.domain.EntityBase;
import br.com.softpethouse.api.domain.pet.PetEntity;
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
