package com.finance.smart_budget.repositories;

import com.finance.smart_budget.entity.IncomeExpenseItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeExpenseItemRepository extends JpaRepository<IncomeExpenseItem, Long> {
}
