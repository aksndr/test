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
    private String sn;

    @Column(name = "descr", nullable = false)
    private String descr;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private CounterType type;

    @Column(name = "value", nullable = false)
    private String value;

    @ManyToOne
    @JoinColumn(name = "flat_id")
    private Flat flat;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
