package com.finance.smart_budget.controllers;

import com.finance.smart_budget.dto.request.AccountRequest;
import com.finance.smart_budget.dto.response.AccountDto;
import com.finance.smart_budget.entity.Account;
import com.finance.smart_budget.mapper.AccountMapper;
import com.finance.smart_budget.services.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountServiceImpl accountService;
    private final AccountMapper accountMapper;

    @PostMapping("/create")
    public ResponseEntity<AccountDto> create(@RequestBody AccountRequest accountRequest) {
        Account account = accountService.createAccount(accountMapper.toEntity(accountRequest));
        return new ResponseEntity<>(accountMapper.toDto(account), HttpStatus.CREATED);
    }
}
