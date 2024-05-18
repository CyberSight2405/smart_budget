package com.finance.smart_budget.entity;

import com.finance.smart_budget.entity.enums.TypeOperation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "operation")
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "card_id", nullable = true)
    private Card card;

    @Column(name = "type_operation")
    @Enumerated(EnumType.STRING)
    private TypeOperation typeOperation;

    @ManyToOne
    @JoinColumn(name = "income_expense_item_id", nullable = true)
    private IncomeExpenseItem incomeExpenseItem;

    @Column(name = "sum", precision = 15, scale = 2)
    private BigDecimal sum;

    @Column(name = "note")
    private String note;

}
