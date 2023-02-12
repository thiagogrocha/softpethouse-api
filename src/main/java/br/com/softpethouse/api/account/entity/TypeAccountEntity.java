package br.com.softpethouse.api.account.entity;

import br.com.softpethouse.api.commom.EntityBase;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "typesaccount")
@SequenceGenerator(name = "TypesAccountSeq", sequenceName = "seq_types_account", allocationSize = 1)
public class TypeAccountEntity extends EntityBase {

    @Id
    @GeneratedValue(generator = "TypesAccountSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

}
