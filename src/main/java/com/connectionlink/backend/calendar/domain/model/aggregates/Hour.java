package com.connectionlink.backend.calendar.domain.model.aggregates;

import jakarta.persistence.*;

@Entity
@EntityListeners(ArithmeticException.class)
public class Hour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String Hour;
}
