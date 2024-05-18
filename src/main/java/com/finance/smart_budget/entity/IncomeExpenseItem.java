package com.finance.smart_budget.entity;

import com.finance.smart_budget.entity.enums.TypeOperation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Table(name = "income_expense_item")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncomeExpenseItem {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type_operation")
    @Enumerated(EnumType.STRING)
    private TypeOperation typeOperation;

    @OneToMany(mappedBy = "incomeExpenseItem")
    private List<Operation> transactions;
}
