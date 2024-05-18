package com.finance.smart_budget.repositories;

import com.finance.smart_budget.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {
}
