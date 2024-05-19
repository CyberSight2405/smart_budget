package com.finance.smart_budget.services;

import com.finance.smart_budget.dto.request.OperationRequest;
import com.finance.smart_budget.dto.response.OperationDto;
import com.finance.smart_budget.entity.Account;
import com.finance.smart_budget.entity.IncomeExpenseItem;
import com.finance.smart_budget.entity.Operation;
import com.finance.smart_budget.entity.enums.TypeOperation;
import com.finance.smart_budget.repositories.AccountRepository;
import com.finance.smart_budget.repositories.IncomeExpenseItemRepository;
import com.finance.smart_budget.repositories.OperationRepository;
import com.finance.smart_budget.utils.ContextUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.finance.smart_budget.utils.ContextUtil.getUsernameFromSecurityContext;

@Service
@RequiredArgsConstructor
public class OperationServiceImpl implements OperationService{
    private final OperationRepository operationRepository;
    private final IncomeExpenseItemRepository itemRepository;
    private final AccountRepository accountRepository;

    @Override
    public void debit(OperationRequest operation) {
        saveOperationAndItem(operation, TypeOperation.INCOME);
    }

    @Override
    public void credit(OperationRequest operation) {
        saveOperationAndItem(operation, TypeOperation.EXPENSE);
    }

    @Override
    public BigDecimal getBalance() {
        Account account = accountRepository.findAccountByUsername(getUsernameFromSecurityContext()).orElse(null);
        if (Objects.isNull(account)) {
            return null;
        }
        return account.getTotalBalance();
    }

    @Override
    public OperationDto getIncome() throws AccountNotFoundException {
        return getAllOperationsWithTypeOperation(TypeOperation.INCOME);
    }

    @Override
    public OperationDto getExpense() throws AccountNotFoundException {
        return getAllOperationsWithTypeOperation(TypeOperation.EXPENSE);
    }

    private OperationDto getAllOperationsWithTypeOperation(TypeOperation typeOperation) throws AccountNotFoundException {
        Account account = accountRepository.findAccountByUsername(getUsernameFromSecurityContext()).orElse(null);
        if (Objects.isNull(account)) {
            throw new AccountNotFoundException("Account not found");
        }
        Operation operation = operationRepository.findByAccountAndTypeOperation(account, typeOperation).orElse(null);
        if (Objects.isNull(operation)) {
            return null;
        }

        return OperationDto.builder()
                .dateTime(operation.getDateTime())
                .sum(operation.getSum())
                .typeOperation(operation.getTypeOperation())
                .build();
    }

    private void saveOperationAndItem(OperationRequest operation, TypeOperation typeOperation) {
        LocalDateTime dateTime = operation.getDateTime() != null ? operation.getDateTime() : LocalDateTime.now();
        Account account = accountRepository.findAccountByUsername(getUsernameFromSecurityContext()).orElse(null);

        if (Objects.isNull(account)) {
            throw new RuntimeException("Account not found");
        }
        if (typeOperation.equals(TypeOperation.INCOME)) {
            account.setTotalBalance(account.getTotalBalance().add(operation.getSum()));
        } else {
            account.setTotalBalance(account.getTotalBalance().subtract(operation.getSum()));
        }

        IncomeExpenseItem incomeExpenseItem = IncomeExpenseItem.builder()
                .name(operation.getName())
                .typeOperation(typeOperation)
                .build();
        IncomeExpenseItem item = itemRepository.save(incomeExpenseItem);


        Operation debitOperation = Operation.builder()
                .account(operation.getAccount())
                .typeOperation(typeOperation)
                .sum(operation.getSum())
                .dateTime(dateTime)
                .incomeExpenseItem(item)
                .note(operation.getNote())
                .build();
        operationRepository.save(debitOperation);

        accountRepository.save(account);
    }

}
