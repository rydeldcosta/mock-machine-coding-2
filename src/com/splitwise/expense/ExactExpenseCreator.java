package com.splitwise.expense;

import com.splitwise.models.Debt;
import com.splitwise.models.ExactPair;
import com.splitwise.models.User;

import java.util.List;
import java.util.stream.Collectors;

public class ExactExpenseCreator implements ExpenseCreator {
    private final User payee;
    private final List<ExactPair> debtors;
    private final Double amount;

    public ExactExpenseCreator(User payee, List<ExactPair> debtors, Double amount) {
        this.payee = payee;
        this.debtors = debtors;
        this.amount = amount;
        this.validate();
    }

    private void validate() {
        double sum = 0;
        for (ExactPair debtor : debtors) {
            sum += debtor.getAmount();
        }
        if (sum != amount) {
            throw new IllegalArgumentException("Expected percentages to add up to Amount, but instead found " + sum);
        }
    }

    @Override
    public Expense createExpense() {
        final List<Debt> debts = debtors.stream()
                .filter(pair -> !payee.getId().equals(pair.getUser().getId()))
                .map(pair -> new Debt(pair.getUser(), pair.getAmount()))
                .collect(Collectors.toList());
        return new Expense(payee, amount, debts);
    }
}
