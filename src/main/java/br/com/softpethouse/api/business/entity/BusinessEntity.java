package br.com.softpethouse.api.business.entity;

import br.com.softpethouse.api.commom.EntityBase;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "business")
@SequenceGenerator(name = "BusinessSeq", sequenceName = "seq_business", allocationSize = 1)
@Check(constraints = "upper(active) in ('S', 'N')")
public class BusinessEntity extends EntityBase {

    @Id
    @GeneratedValue(generator = "BusinessSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String active;

    public BusinessEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @PrePersist
    private void prePersist() {
        setActive("S");
    }
}
