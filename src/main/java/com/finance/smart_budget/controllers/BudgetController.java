package com.finance.smart_budget.controllers;

import com.finance.smart_budget.dto.request.OperationRequest;
import com.finance.smart_budget.repositories.OperationRepository;
import com.finance.smart_budget.services.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/budget")
@RequiredArgsConstructor
public class BudgetController {
    private final OperationService operationService;

    @PostMapping("/debit")
    public void debit(@RequestBody OperationRequest operationRequest){
        operationService.debit(operationRequest);
    }

    @PostMapping("/credit")
    public void credit(@RequestBody OperationRequest operationRequest){
        operationService.debit(operationRequest);
    }
}
