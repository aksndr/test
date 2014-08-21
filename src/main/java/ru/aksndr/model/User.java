package ru.aksndr.model;

import com.fluentinterface.ReflectionBuilder;
import com.fluentinterface.builder.Builder;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by aksndr on 11.08.14.
 */
@Entity
@Table(name = "users")
public class User {
    public static UserBuilder create() {
        return ReflectionBuilder.implementationFor(UserBuilder.class).create();
    }

    public interface UserBuilder extends Builder<User> {
        public UserBuilder withLogin(String login);

        public UserBuilder withFirstname(String firstname);
        public UserBuilder withLastname(String lastname);
    }

    public User() {
    }

    public User(String login, String firstname, String lastname) {
        this.login = login;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "login")
    private String login;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;

    @OneToOne
    @JoinColumn(name = "flat_id")
    private Flat flat;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Flat getFlat() {
        return flat;
    }

    public void setFlat(Flat flat) {
        this.flat = flat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof User)
                && Objects.equals(getFirstname(), ((User) obj).getFirstname())
                && Objects.equals(getLastname(), ((User) obj).getLastname());
    }
}
