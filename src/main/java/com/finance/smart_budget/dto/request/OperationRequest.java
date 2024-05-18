package com.finance.smart_budget.dto.request;

import com.finance.smart_budget.entity.IncomeExpenseItem;
import com.finance.smart_budget.entity.enums.TypeOperation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@Setter
public class OperationRequest {
    private String account_id;
    private LocalDateTime date_time;
    private Long card_id;
    private TypeOperation typeOperation;
    private IncomeExpenseItem incomeExpenseItemId;
}
