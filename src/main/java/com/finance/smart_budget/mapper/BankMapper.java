package com.finance.smart_budget.mapper;

import com.finance.smart_budget.dto.request.BankRequest;
import com.finance.smart_budget.dto.response.BankDto;
import com.finance.smart_budget.entity.Bank;
import org.springframework.stereotype.Component;

@Component
public class BankMapper {

    public Bank toEntity(BankRequest request) {
        return Bank.builder()
                .name(request.getName())
                .externalId(request.getExternalId())
                .build();
    }

    public BankDto toDto(Bank bank) {
        return BankDto.builder()
                .id(bank.getId())
                .name(bank.getName())
                .externalId(bank.getExternalId())
                .build();
    }
}
