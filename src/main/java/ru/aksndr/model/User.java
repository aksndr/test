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

        public UserBuilder withAge(int age);
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "age")
    private int age;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
