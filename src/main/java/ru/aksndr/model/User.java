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
        public UserBuilder withFirstname(String firstname);

        public UserBuilder withLastname(String lastname);

        public UserBuilder withFlatId(Long age);
    }

    public User() {
    }

    public User(String firstname, String lastname, Long flatid) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.flatid = flatid;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "flatid")
    private Long flatid;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Long getFlatid() {
        return flatid;
    }

    public void setFlatid(Long flatid) {
        this.flatid = flatid;
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
