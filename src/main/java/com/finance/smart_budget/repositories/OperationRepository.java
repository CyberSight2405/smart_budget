package com.finance.smart_budget.repositories;

import com.finance.smart_budget.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}
