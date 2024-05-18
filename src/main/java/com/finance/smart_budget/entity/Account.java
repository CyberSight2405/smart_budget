package com.finance.smart_budget.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

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
    private BigDecimal total_balance;

    @Column(name = "cash_balance", precision = 15, scale = 2)
    private BigDecimal cash_balance;

    @Column(name = "cashless_balance", precision = 15, scale = 2)
    private BigDecimal cashless_balance;

}
