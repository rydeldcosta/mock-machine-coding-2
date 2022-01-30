package com.splitwise.expense;

import com.splitwise.models.Debt;
import com.splitwise.models.User;

import java.util.List;
import java.util.stream.Collectors;

public class EqualExpenseCreator implements ExpenseCreator {
    private final User payee;
    private final List<User> debtors;
    private final Double amount;

    public EqualExpenseCreator(User payee, List<User> debtors, Double amount) {
        this.payee = payee;
        this.debtors = debtors;
        this.amount = amount;
        this.validate();
    }

    private void validate() {
        // nothing to validate since equal
    }

    @Override
    public Expense createExpense() {
        int totalDebtors = debtors.size();
        double share = amount / totalDebtors;
        final List<Debt> debts = debtors.stream()
                .filter(user -> !payee.getId().equals(user.getId()))
                .map(user -> new Debt(user, share))
                .collect(Collectors.toList());
        return new Expense(payee, amount, debts);
    }
}
