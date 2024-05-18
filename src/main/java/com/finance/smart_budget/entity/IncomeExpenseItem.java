package com.finance.smart_budget.entity;

import com.finance.smart_budget.entity.enums.TypeOperation;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "income_expense_item")
public class IncomeExpenseItem {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    @Id
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "type_operation")
    private TypeOperation typeOperation;
}
