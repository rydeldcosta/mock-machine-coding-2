package com.splitwise.handlers;

import com.splitwise.datastore.UserDatabase;
import com.splitwise.expense.Expense;
import com.splitwise.expense.ExpenseCreator;
import com.splitwise.expense.PercentExpenseCreator;
import com.splitwise.models.PercentPair;
import com.splitwise.models.User;

import java.util.ArrayList;
import java.util.List;

public class PercentExpenseHandler implements ExpenseHandler {
    UserDatabase userDatabase;

    public PercentExpenseHandler(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    public Expense handle(String[] commands, int numDebtors) {
        User payee = userDatabase.lookup(commands[1]);
        Double amount = Double.parseDouble(commands[2]);
        List<PercentPair> debtors = new ArrayList<>();
        for (int i = numDebtors + 5; i < commands.length; i++) {
            Double percent = Double.parseDouble(commands[i]);
            debtors.add(new PercentPair(userDatabase.lookup(commands[i - numDebtors - 1]), percent));
        }
        ExpenseCreator creator = new PercentExpenseCreator(payee, debtors, amount);
        return creator.createExpense();
    }
}
