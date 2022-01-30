package com.splitwise.expense;

import com.splitwise.models.Debt;
import com.splitwise.models.User;

import java.util.List;

public interface ExpenseCreator {

    /** Balances and updates the User bank records
     *
     */
    Expense createExpense();
}
