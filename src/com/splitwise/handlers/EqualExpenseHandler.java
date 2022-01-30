package com.splitwise.handlers;

import com.splitwise.datastore.UserDatabase;
import com.splitwise.expense.EqualExpenseCreator;
import com.splitwise.expense.Expense;
import com.splitwise.expense.ExpenseCreator;
import com.splitwise.models.User;

import java.util.ArrayList;
import java.util.List;

// EXPENSE u1 1000 4 u1 u2 u3 u4 EQUAL
public class EqualExpenseHandler implements ExpenseHandler {
    UserDatabase userDatabase;

    public EqualExpenseHandler(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    public Expense handle(String[] commands, int numDebtors) {
        User payee = userDatabase.lookup(commands[1]);
        Double amount = Double.parseDouble(commands[2]);
        List<User> debtors = new ArrayList<>();
        for (int i = 4; i < 4 + numDebtors; i++) {
            debtors.add(userDatabase.lookup(commands[i]));
        }
        ExpenseCreator creator = new EqualExpenseCreator(payee, debtors, amount);
        return creator.createExpense();
    }
}
