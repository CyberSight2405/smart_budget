package com.finance.smart_budget.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "account_cards")
@Data
public class AccountCards {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;
}
