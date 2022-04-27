package br.com.softpethouse.api.pet;

import br.com.softpethouse.api.commom.EntityBase;
import lombok.Data;

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
    public void prePersist() {
        if (dateTime == null)
            dateTime = LocalDateTime.now();
    }

}
