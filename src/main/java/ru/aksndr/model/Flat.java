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
    @Column(name = "houseid", nullable = false)
    private Long houseid;
    @Column(name = "flatnum", nullable = false)
    private String flatnum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHouseid() {
        return houseid;
    }

    public void setHouseid(Long houseid) {
        this.houseid = houseid;
    }

    public String getFlatnum() {
        return flatnum;
    }

    public void setFlatnum(String flatnum) {
        this.flatnum = flatnum;
    }
}
