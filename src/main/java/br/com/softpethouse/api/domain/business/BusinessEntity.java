package br.com.softpethouse.api.domain.business;

import br.com.softpethouse.api.domain.EntityBase;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "business")
@SequenceGenerator(name = "BusinessSeq", sequenceName = "seq_business", allocationSize = 1)
public class BusinessEntity extends EntityBase {

    @Id
    @GeneratedValue(generator = "BusinessSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

}
