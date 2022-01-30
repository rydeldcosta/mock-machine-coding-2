package com.splitwise.expense;

import com.splitwise.models.Debt;
import com.splitwise.models.User;

import java.util.List;

public class Expense {
    private final User user;
    private final Double amount;
    private final List<Debt> debts;

    public Expense(User user, Double amount, List<Debt> debts) {
        this.user = user;
        this.amount = amount;
        this.debts = debts;
    }

    public User getUser() {
        return user;
    }

    public Double getAmount() {
        return amount;
    }

    public List<Debt> getDebts() {
        return debts;
    }
}
