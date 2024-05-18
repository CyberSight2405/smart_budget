package com.finance.smart_budget.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Table(name = "accounts")
@Entity
public class Account {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    @Id
    private Long id;
    @Column(name = "number")
    private String number;
    @Column(name = "total_balance")
    private BigDecimal total_balance;
    @Column(name = "cach_balance")
    private BigDecimal cach_balance;
    @Column(name = "cachless_balance")
    private BigDecimal cachless_balance;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AccountCards> accountCards;
}
