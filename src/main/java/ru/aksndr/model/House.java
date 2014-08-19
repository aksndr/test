package ru.aksndr.model;

import javax.persistence.*;

/**
 * Created by aksndr on 19.08.14.
 */
@Entity
@Table(name = "houses")
public class House {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "address")
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
