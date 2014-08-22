package ru.aksndr.model;

import javax.persistence.*;

/**
 * Created by aksndr on 19.08.14.
 */
@Entity
@Table(name = "flats")
public class Flat {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;
    @Column(name = "flat_num", nullable = false)
    private String flatNum;
//    @OneToMany(mappedBy = "flat")
//    private Set<Counter> counters;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public String getFlatnum() {
        return flatNum;
    }

    public void setFlatnum(String flatNum) {
        this.flatNum = flatNum;
    }

//    public Set<Counter> getCounters() {
//        return counters;
//    }
//
//    public void setCounters(Set<Counter> counters) {
//        this.counters = counters;
//    }
}
