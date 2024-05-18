package com.finance.smart_budget.dto.response;

import jakarta.persistence.Entity;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
public class AccountDto {
    private BigDecimal cashBalance;
    private BigDecimal cashlessBalance;
    private BigDecimal totalBalance;
    private String number;
}
