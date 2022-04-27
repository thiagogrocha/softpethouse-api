package br.com.softpethouse.api.user;

import br.com.softpethouse.api.commom.EntityBase;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
@SequenceGenerator(name = "UsersSeq", sequenceName = "seq_users", allocationSize = 1)
public class UserEntity extends EntityBase {

    @Id
    @GeneratedValue(generator = "UsersSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

}
