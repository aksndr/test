package ru.aksndr.model;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
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

    @Column(name = "date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private DateTime date;

    @ManyToOne
    @JoinColumn(name = "counterid")
    private Counter counter;

    @Column(name = "value", nullable = false)
    private BigDecimal value;

    public Counter getCounterid() {
        return counter;
    }

    public void setCounterid(Counter counter) {
        this.counter = counter;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDate(), getCounterid());
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Record)
                && Objects.equals(getId(), ((Record) obj).getId())
                && Objects.equals(getCounterid(), ((Record) obj).getCounterid())
                && Objects.equals(getDate(), ((Record) obj).getDate())
                && Objects.equals(getValue(), ((Record) obj).getValue());
    }
}
