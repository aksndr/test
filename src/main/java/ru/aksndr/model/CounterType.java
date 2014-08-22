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

    @Column(name = "type_name", nullable = false)
    private String typeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
