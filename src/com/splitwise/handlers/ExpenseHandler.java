package com.splitwise.handlers;

import com.splitwise.expense.Expense;

public interface ExpenseHandler {

    Expense handle(String[] commands, int numDebtors);
}
