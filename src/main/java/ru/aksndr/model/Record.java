package ru.aksndr.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * User: a.arzamastsev Date: 26.08.14 Time: 16:21
 */
@Entity
@Table(name = "records")
public class Record {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "datetime")
    private String datetime;

    @ManyToOne
    @JoinColumn(name = "counterid")
    private Counter counter;

    @Column(name = "value", nullable = false)
    private Integer value;

    public Counter getCounter() {
        return counter;
    }

    public void setCounter(Counter counter) {
        this.counter = counter;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDatetime(), getCounter().getSn());
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Record)
                && Objects.equals(getId(), ((Record) obj).getId())
                && Objects.equals(getCounter().getSn(), ((Record) obj).getCounter().getSn())
                && Objects.equals(getDatetime(), ((Record) obj).getDatetime())
                && Objects.equals(getValue(), ((Record) obj).getValue());
    }
}
