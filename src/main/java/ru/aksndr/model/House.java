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

    public House() {
        super();
    }

    public House(String address) {
        super();
        this.address = address;
    }

//    @OneToMany(mappedBy = "house")
//    private Set<Flat> flats;

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

//    public Set<Flat> getFlats() {
//        return flats;
//    }
//
//    public void setFlats(Set<Flat> flats) {
//        this.flats = flats;
//    }
}
