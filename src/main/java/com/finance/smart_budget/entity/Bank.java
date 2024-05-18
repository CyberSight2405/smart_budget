package com.finance.smart_budget.entity;

import jakarta.persistence.*;

@Entity
public class Bank {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    @Id
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "external_id")
    private String externalId;
}
