package com.finance.smart_budget.repositories;

import com.finance.smart_budget.entity.Account;
import com.finance.smart_budget.entity.Operation;
import com.finance.smart_budget.entity.enums.TypeOperation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    Optional<Operation> findByAccountAndTypeOperation(Account account, TypeOperation typeOperation);
}
