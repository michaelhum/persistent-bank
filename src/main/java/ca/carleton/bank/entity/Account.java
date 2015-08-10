package ca.carleton.bank.entity;

import javax.persistence.*;

/**
 * Represents an account a user might hve.
 *
 * Created by Mike on 10/08/2015.
 */
@Entity
@Table(name = "ACCOUNTS")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgen")
    @SequenceGenerator(name = "idgen", sequenceName = "ACCOUNT_ID_SEQ", allocationSize = 1)
    @Column(name = "ID", nullable = false, unique = true, updatable = false)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "ACCOUNT_TYPE", updatable = false)
    private AccountType accountType;

    // ignore the fact money using floating points wah wah
    @Column(name = "BALANCE", nullable = false)
    private double balance;

    @ManyToOne
    @JoinColumn(name = "USER_ID", updatable = false, nullable = false)
    private User accountOwner;

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public User getAccountOwner() {
        return this.accountOwner;
    }

    public void setAccountOwner(final User accountOwner) {
        this.accountOwner = accountOwner;
    }

    public AccountType getAccountType() {
        return this.accountType;
    }

    public void setAccountType(final AccountType accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(final double balance) {
        this.balance = balance;
    }

    public String toString() {
        return String.format("Account type: %s. Balance: %f", this.accountType, this.balance);
    }
}
