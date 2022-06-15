package br.com.softpethouse.api.historic.entity;

import lombok.Data;
import br.com.softpethouse.api.commom.EntityBase;
import br.com.softpethouse.api.pet.entity.PetEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "historical")
@SequenceGenerator(name = "HistoricalSeq", sequenceName = "seq_historical", allocationSize = 1)
public class HistoricEntity extends EntityBase {

    @Id
    @GeneratedValue(generator = "HistoricalSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idpet")
    private PetEntity pet;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @PrePersist
    private void setDefaultDateTime() {
        if (dateTime == null)
            dateTime = LocalDateTime.now();
    }

}
