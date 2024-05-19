package com.finance.smart_budget.services;

import com.finance.smart_budget.entity.Bank;
import com.finance.smart_budget.repositories.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;

    @Override
    public Bank create(Bank bank) {
        return bankRepository.save(bank);
    }
}
