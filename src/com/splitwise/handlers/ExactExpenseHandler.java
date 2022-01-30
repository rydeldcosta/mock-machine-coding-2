package com.splitwise.handlers;

import com.splitwise.datastore.UserDatabase;
import com.splitwise.expense.ExactExpenseCreator;
import com.splitwise.expense.Expense;
import com.splitwise.expense.ExpenseCreator;
import com.splitwise.models.ExactPair;
import com.splitwise.models.User;

import java.util.ArrayList;
import java.util.List;

//EXPENSE u1 1250 2 u2 u3 EXACT 370 880
public class ExactExpenseHandler implements ExpenseHandler {
    UserDatabase userDatabase;

    public ExactExpenseHandler(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    public Expense handle(String[] commands, int numDebtors) {
        User payee = userDatabase.lookup(commands[1]);
        Double amount = Double.parseDouble(commands[2]);
        List<ExactPair> debtors = new ArrayList<>();
        for (int i = numDebtors + 5; i < commands.length; i++) {
            Double debtAmount = Double.parseDouble(commands[i]);
            debtors.add(new ExactPair(userDatabase.lookup(commands[i - numDebtors - 1]), debtAmount));
        }
        ExpenseCreator creator = new ExactExpenseCreator(payee, debtors, amount);
        return creator.createExpense();
    }
}
