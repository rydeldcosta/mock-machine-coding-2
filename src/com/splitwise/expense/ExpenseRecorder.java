package com.splitwise.expense;

import com.splitwise.datastore.Bank;

public class ExpenseRecorder {

    private final Bank bank;

    public ExpenseRecorder(Bank bank) {
        this.bank = bank;
    }

    public void record(Expense expense) {
        expense.getDebts().forEach(debt -> {
            bank.recordTransaction(expense.getUser(), debt.getDebtOwner(), debt.getAmount());
        });
    }
}
