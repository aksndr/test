package ru.aksndr.model;

import javax.persistence.*;

/**
 * Created by aksndr on 21.08.14.
 */
@Entity
@Table(name = "countertypes")
public class CounterType {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "typename", nullable = false)
    private String typename;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }
}
