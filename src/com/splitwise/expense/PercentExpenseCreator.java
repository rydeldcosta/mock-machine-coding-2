package com.splitwise.expense;

import com.splitwise.models.Debt;
import com.splitwise.models.PercentPair;
import com.splitwise.models.User;

import java.util.List;
import java.util.stream.Collectors;

//EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20
public class PercentExpenseCreator implements ExpenseCreator {
    private final User payee;
    private final List<PercentPair> debtors;
    private final Double amount;

    public PercentExpenseCreator(User payee, List<PercentPair> debtors, Double amount) {
        this.payee = payee;
        this.debtors = debtors;
        this.amount = amount;
        this.validate();
    }

    private void validate() {
        double sum = 0;
        for (PercentPair debtor : debtors) {
            sum += debtor.getPercent();
        }
        if (sum != 100) {
            throw new IllegalArgumentException("Expected percentages to add up to 100, but instead found " + sum);
        }
    }

    @Override
    public Expense createExpense() {
        final List<Debt> debts = debtors.stream()
                .filter(pair -> !payee.getId().equals(pair.getUser().getId()))
                .map(pair -> {
                    double share = pair.getPercent() * amount / 100;
                    return new Debt(pair.getUser(), share);
                })
                .collect(Collectors.toList());
        return new Expense(payee, amount, debts);
    }
}
