package com.finance.smart_budget.entity;

import com.finance.smart_budget.entity.enums.TypeOperation;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "operation")
public class Operation {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
    @Column(name = "date_time")
    private LocalDateTime dateTime;
    @Column(name = "card_id")
    private String cardId;
    @Column(name = "type_operation")
    private TypeOperation typeOperation;
    @Column(name = "income_expense_item_id")
    private String incomeExpenseItemId;
    @Column(name = "sum")
    private BigDecimal sum;
    @Column(name = "note")
    private String note;

}
