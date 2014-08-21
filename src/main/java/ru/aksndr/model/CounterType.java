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

    @Column(name = "typename", nullable = false)
    private String typename;
}
