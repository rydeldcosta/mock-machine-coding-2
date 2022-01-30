package com.splitwise.handlers;

import com.splitwise.datastore.UserDatabase;
import com.splitwise.models.ExpenseType;

public class ExpenseHandlerFactory {
    private final ExactExpenseHandler exactExpenseHandler;
    private final EqualExpenseHandler equalExpenseHandler;
    private final PercentExpenseHandler percentExpenseHandler;

    public ExpenseHandlerFactory(final UserDatabase database) {
        exactExpenseHandler = new ExactExpenseHandler(database);
        equalExpenseHandler = new EqualExpenseHandler(database);
        percentExpenseHandler = new PercentExpenseHandler(database);
    }

    public ExpenseHandler getExpenseHandler(final ExpenseType expenseType) {
        switch (expenseType) {
            case PERCENT:
                return percentExpenseHandler;
            case EQUAL:
                return equalExpenseHandler;
            case EXACT:
                return exactExpenseHandler;
            default:
                throw new IllegalArgumentException("Unknown expense type: " + expenseType);
        }
    }
}
