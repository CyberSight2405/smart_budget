package com.finance.smart_budget.dto.request;

import com.finance.smart_budget.entity.Account;
import com.finance.smart_budget.entity.Card;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class OperationRequest {

    private Account account;

    private LocalDateTime dateTime;

    private Card card;

    private BigDecimal sum;

    private String name;

    private String note;
}
