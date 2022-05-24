package br.com.softpethouse.api.account.entity;

import br.com.softpethouse.api.commom.EntityBase;
import br.com.softpethouse.api.user.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "accounts")
@SequenceGenerator(name = "AccountsSeq", sequenceName = "seq_accounts", allocationSize = 1)
@Check(constraints = "upper(active) in ('S', 'N')")
public class AccountEntity extends EntityBase {

    @Id
    @GeneratedValue(generator = "AccountsSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "iduser")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "idtypeaccount")
    private TypeAccountEntity typeAccount;

    @ManyToOne
    @JoinColumn(name = "idbusiness")
    private BusinessEntity business;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @ColumnDefault(value = "S")
    private String active;

    public AccountEntity(UserEntity user, TypeAccountEntity typeAccount, BusinessEntity business, String username, String email, String password) {
        this.user = user;
        this.typeAccount = typeAccount;
        this.business = business;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
