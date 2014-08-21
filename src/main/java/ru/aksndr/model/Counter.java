package ru.aksndr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by aksndr on 19.08.14.
 */
@Entity
@Table(name = "counters")
public class Counter {
    @Id
    @Column(name = "sn", nullable = false, unique = true)
    private String sn;

    @Column(name = "descr", nullable = false)
    private String descr;

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}
