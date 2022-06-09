package br.com.softpethouse.api.business.entity;

import br.com.softpethouse.api.commom.EntityBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "business")
@SequenceGenerator(name = "BusinessSeq", sequenceName = "seq_business", allocationSize = 1)
public class BusinessEntity extends EntityBase {

    @Id
    @GeneratedValue(generator = "BusinessSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

}
