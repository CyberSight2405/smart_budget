package com.finance.smart_budget.dto.response;

import com.finance.smart_budget.entity.enums.TypeOperation;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class OperationResponse {

    private BigDecimal sum;

    private TypeOperation typeOperation;

    private LocalDateTime dateTime;
}
