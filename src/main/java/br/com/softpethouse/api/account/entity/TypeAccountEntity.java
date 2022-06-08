package br.com.softpethouse.api.account.entity;

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
@Table(name = "typesAccount")
@SequenceGenerator(name = "TypesAccountSeq", sequenceName = "seq_typesAccount", allocationSize = 1)
public class TypeAccountEntity extends EntityBase {

    @Id
    @GeneratedValue(generator = "TypesAccountSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

}
