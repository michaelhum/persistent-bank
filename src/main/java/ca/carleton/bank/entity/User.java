package ca.carleton.bank.entity;

import javax.persistence.*;

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

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String toString() {
        return String.format("User: [%s:%s]", this.userName, this.password);
    }

}
