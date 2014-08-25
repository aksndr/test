package ru.aksndr.model;

import javax.persistence.*;

/**
 * Created by aksndr on 19.08.14.
 */
@Entity
@Table(name = "counters")
public class Counter {
    @Id
    @Column(name = "sn", nullable = false, unique = true)
    private Long sn;

    @Column(name = "descr", nullable = false)
    private String descr;

    @OneToOne
    @JoinColumn(name = "typeid")
    private CounterType type;

    @ManyToOne
    @JoinColumn(name = "flatid")
    private Flat flat;

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Long getSn() {
        return sn;
    }

    public void setSn(Long sn) {
        this.sn = sn;
    }

    public CounterType getType() {
        return type;
    }

    public void setType(CounterType type) {
        this.type = type;
    }

    public Flat getFlat() {
        return flat;
    }

    public void setFlat(Flat flat) {
        this.flat = flat;
    }
}
