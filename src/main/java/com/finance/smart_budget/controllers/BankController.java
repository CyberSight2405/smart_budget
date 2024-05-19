package com.finance.smart_budget.controllers;

import com.finance.smart_budget.dto.request.BankRequest;
import com.finance.smart_budget.dto.response.BankDto;
import com.finance.smart_budget.entity.Bank;
import com.finance.smart_budget.mapper.BankMapper;
import com.finance.smart_budget.services.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banks")
@RequiredArgsConstructor
public class BankController {

    private final BankService bankService;
    private final BankMapper bankMapper;

    @PostMapping()
    public ResponseEntity<BankDto> createBank(@RequestBody BankRequest request) {
        Bank bank = bankService.create(bankMapper.toEntity(request));
        return new ResponseEntity<>(bankMapper.toDto(bank), HttpStatus.CREATED);
    }

}
