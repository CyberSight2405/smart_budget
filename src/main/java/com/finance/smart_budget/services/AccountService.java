package com.finance.smart_budget.services;

import com.finance.smart_budget.dto.request.AccountRequest;
import com.finance.smart_budget.dto.response.AccountDto;
import com.finance.smart_budget.entity.Account;

public interface AccountService {
    Account createAccount(Account account);
}
