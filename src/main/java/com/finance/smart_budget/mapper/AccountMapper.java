package com.finance.smart_budget.mapper;

import com.finance.smart_budget.dto.request.AccountRequest;
import com.finance.smart_budget.dto.response.AccountDto;
import com.finance.smart_budget.entity.Account;
import org.springframework.stereotype.Component;


@Component
public class AccountMapper {

    public Account toEntity(AccountRequest request) {
        return Account.builder()
                .cachBalance(request.getCashBalance())
                .cachlessBalance(request.getCashlessBalance())
                .totalBalance(request.getTotalBalance())
                .number(request.getNumber())
                .build();
    }

    public AccountDto toDto(Account request) {
        return AccountDto.builder()
                .cashBalance(request.getCachBalance())
                .cashlessBalance(request.getCachlessBalance())
                .totalBalance(request.getTotalBalance())
                .number(request.getNumber())
                .build();
    }
}
