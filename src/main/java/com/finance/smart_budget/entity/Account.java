package com.finance.smart_budget.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "account", indexes = {@Index(name = "account_username_index", columnList = "username")})
@Entity
public class Account {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    @Id
    private Long id;

    @Column(name = "number", length = 50)
    private String number;

    @Column(name = "username")
    private String username;

    @Column(name = "total_balance")
    private BigDecimal totalBalance;

    @Column(name = "cash_balance", precision = 15, scale = 2)
    private BigDecimal cachBalance;

    @Column(name = "cashless_balance", precision = 15, scale = 2)
    private BigDecimal cachlessBalance;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> cards = new ArrayList<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Operation> operations = new ArrayList<>();
}
