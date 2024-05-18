package com.finance.smart_budget.services;

import com.finance.smart_budget.dto.request.OperationRequest;
import com.finance.smart_budget.dto.response.OperationDto;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;

public interface OperationService {

    void debit(OperationRequest operation);

    void credit(OperationRequest operation);

    BigDecimal getBalance();

    OperationDto getIncome() throws AccountNotFoundException;

    OperationDto getExpense() throws AccountNotFoundException;
}
