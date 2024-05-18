package com.finance.smart_budget.controllers;

import com.finance.smart_budget.dto.request.OperationRequest;
import com.finance.smart_budget.dto.response.OperationResponse;
import com.finance.smart_budget.services.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;

@RestController
@RequestMapping("/budget")
@RequiredArgsConstructor
public class BudgetController {
    private final OperationService operationService;

    @PostMapping("/debit")
    public void debit(@RequestBody OperationRequest operationRequest) {
        operationService.debit(operationRequest);
    }

    @PostMapping("/credit")
    public void credit(@RequestBody OperationRequest operationRequest) {
        operationService.credit(operationRequest);
    }

    @GetMapping("/balance")
    public ResponseEntity<BigDecimal> getBalance() {
        return ResponseEntity.ok(operationService.getBalance());
    }

    @GetMapping("/income")
    public ResponseEntity<OperationResponse> getIncome() throws AccountNotFoundException {
        return ResponseEntity.ok(operationService.getIncome());
    }

    @GetMapping("/expense")
    public ResponseEntity<OperationResponse> getExpense() throws AccountNotFoundException {
        return ResponseEntity.ok(operationService.getExpense());
    }
}
