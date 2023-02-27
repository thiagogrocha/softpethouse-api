package br.com.softpethouse.api.domain.account;

import br.com.softpethouse.api.domain.business.BusinessEntity;
import br.com.softpethouse.api.domain.EntityBase;
import br.com.softpethouse.api.domain.user.UserEntity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "accounts")
@SequenceGenerator(name = "AccountsSeq", sequenceName = "seq_accounts", allocationSize = 1)
public class AccountEntity extends EntityBase {

    @Id
    @GeneratedValue(generator = "AccountsSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "typeaccount_id")
    private TypeAccountEntity typeAccount;

    @ManyToOne
    @JoinColumn(name = "business_id")
    private BusinessEntity business;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

}
