package ca.carleton.bank.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user of the application.
 *
 * Created by Mike on 06/08/2015.
 */
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgen")
    @SequenceGenerator(name = "idgen", sequenceName = "USER_ID_SEQ", allocationSize = 1)
    @Column(name = "ID", nullable = false, unique = true, updatable = false)
    private int id;

    @Column(name = "USERNAME", nullable = false, unique = true)
    private String userName;

    @Column(name = "PASSWORD", nullable = false, unique = true)
    private String password;

    @OneToMany(mappedBy = "accountOwner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Account> accounts;

    public void addAccount(final Account account) {
        if (this.accounts == null) {
            this.accounts = new ArrayList<>();
        }
        this.accounts.add(account);
    }

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public List<Account> getAccounts() {
        return this.accounts;
    }

    public void setAccounts(final List<Account> accounts) {
        this.accounts = accounts;
    }

    public String toString() {
        return String.format("User: [%s:%s]\nAccounts: %s", this.userName, this.password, this.accounts);
    }

}
