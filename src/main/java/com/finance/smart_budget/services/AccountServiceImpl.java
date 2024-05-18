package com.finance.smart_budget.services;

import com.finance.smart_budget.dto.request.AccountRequest;
import com.finance.smart_budget.dto.response.AccountDto;
import com.finance.smart_budget.entity.Account;
import com.finance.smart_budget.repositories.AccountRepository;
import com.finance.smart_budget.utils.ContextUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.finance.smart_budget.utils.ContextUtil.getUsernameFromSecurityContext;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;

    @Override
    public Account createAccount(Account account) {
        account.setNumber(UUID.randomUUID().toString().substring(0, 8));
        account.setUsername(getUsernameFromSecurityContext());
        return accountRepository.save(account);
    }
}
