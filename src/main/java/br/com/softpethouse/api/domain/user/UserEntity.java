package br.com.softpethouse.api.domain.user;

import lombok.Builder;
import lombok.Data;
import br.com.softpethouse.api.domain.EntityBase;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
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
    private LocalDate dtBirth;

}
