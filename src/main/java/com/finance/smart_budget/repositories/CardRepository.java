package com.finance.smart_budget.repositories;

import com.finance.smart_budget.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
