package com.finance.smart_budget.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;
    @Column(name = "bank_id")
    private String bankId;
    @Column(name = "balance")
    private String balance;
    @Column(name = "number")
    private String number;
    @Column(name = "valid_until")
    private String valid_until;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AccountCards> accountCards;
}
