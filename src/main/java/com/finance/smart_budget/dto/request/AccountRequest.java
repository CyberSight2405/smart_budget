package com.finance.smart_budget.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountRequest {
    private BigDecimal cashBalance;
    private BigDecimal cashlessBalance;
    private BigDecimal totalBalance;
    private String number;
}
