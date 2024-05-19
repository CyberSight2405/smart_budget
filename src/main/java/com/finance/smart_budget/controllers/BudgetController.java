package com.finance.smart_budget.controllers;

import com.finance.smart_budget.dto.request.OperationRequest;
import com.finance.smart_budget.dto.response.OperationDto;
import com.finance.smart_budget.services.OperationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;

@RestController
@RequestMapping("/budget")
@RequiredArgsConstructor
public class BudgetController {
    private final OperationServiceImpl operationServiceImpl;

    @PostMapping("/debit")
    public void debit(@RequestBody OperationRequest operationRequest) {
        operationServiceImpl.debit(operationRequest);
    }

    @PostMapping("/credit")
    public void credit(@RequestBody OperationRequest operationRequest) {
        operationServiceImpl.credit(operationRequest);
    }

    @GetMapping("/balance")
    public ResponseEntity<BigDecimal> getBalance() {
        return ResponseEntity.ok(operationServiceImpl.getBalance());
    }

    @GetMapping("/income")
    public ResponseEntity<OperationDto> getIncome() throws AccountNotFoundException {
        return ResponseEntity.ok(operationServiceImpl.getIncome());
    }

    @GetMapping("/expense")
    public ResponseEntity<OperationDto> getExpense() throws AccountNotFoundException {
        return ResponseEntity.ok(operationServiceImpl.getExpense());
    }
}
