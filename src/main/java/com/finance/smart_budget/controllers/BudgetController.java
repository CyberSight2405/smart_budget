package com.finance.smart_budget.controllers;

import com.finance.smart_budget.configuration.JwtService;
import com.finance.smart_budget.dto.request.OperationRequest;
import com.finance.smart_budget.services.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/budget")
@RequiredArgsConstructor
public class BudgetController {
    private final OperationService operationService;
    private final JwtService jwtService;

    @PostMapping("/debit")
    public void debit(@RequestBody OperationRequest operationRequest){
        operationService.debit(operationRequest);
    }

    @PostMapping("/credit")
    public void credit(@RequestBody OperationRequest operationRequest){
        operationService.credit(operationRequest);
    }

    @GetMapping("/balance")
    public ResponseEntity<BigDecimal> getBalance(@RequestParam(value = "id") Long id){
        return ResponseEntity.ok(operationService.getBalance(id));
    }
}
