package br.com.softpethouse.api.account.entity;

import lombok.Data;
import br.com.softpethouse.api.business.entity.BusinessEntity;
import br.com.softpethouse.api.commom.EntityBase;
import br.com.softpethouse.api.user.entity.UserEntity;

import javax.persistence.*;

@Data
@Entity
@Table(name = "accounts")
@SequenceGenerator(name = "AccountsSeq", sequenceName = "seq_accounts", allocationSize = 1)
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

}
