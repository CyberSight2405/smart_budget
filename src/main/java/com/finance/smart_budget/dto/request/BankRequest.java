package com.finance.smart_budget.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BankRequest {
    private String name;
    private String externalId;
}
