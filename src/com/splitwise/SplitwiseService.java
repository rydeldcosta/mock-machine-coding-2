package com.splitwise;

import com.splitwise.datastore.Bank;
import com.splitwise.datastore.UserDatabase;
import com.splitwise.expense.Expense;
import com.splitwise.expense.ExpenseRecorder;
import com.splitwise.handlers.ExpenseHandler;
import com.splitwise.handlers.ExpenseHandlerFactory;
import com.splitwise.models.Balance;
import com.splitwise.models.ExpenseType;
import com.splitwise.models.User;

import java.util.Arrays;
import java.util.List;

public class SplitwiseService {
    private static final String LOG_DELIMITER = " ";
    private final Bank bank;
    private final ExpenseRecorder expenseRecorder;
    private final UserDatabase userDatabase;
    private final ExpenseHandlerFactory expenseHandlerFactory;

    public SplitwiseService() {
        this.bank = new Bank();
        this.expenseRecorder = new ExpenseRecorder(bank);
        this.userDatabase = new UserDatabase();
        this.expenseHandlerFactory = new ExpenseHandlerFactory(userDatabase);
    }

    public void registerUser(User user) {
        userDatabase.register(user);
        System.out.println("Registered User: " + user);
    }

    public void executeTransaction(String transactionLog) {
        // EXPENSE u1 1000 4 u1 u2 u3 u4 EQUAL
        String[] commands = transactionLog.split(LOG_DELIMITER);
        System.out.println("Executing transaction, " + Arrays.toString(commands));
        int numDebtors = Integer.parseInt(commands[3]);
        ExpenseType expenseType = ExpenseType.valueOf(commands[numDebtors + 4]);
        ExpenseHandler expenseHandler = expenseHandlerFactory.getExpenseHandler(expenseType);
        Expense expense = expenseHandler.handle(commands, numDebtors);
        expenseRecorder.record(expense);
    }

    public void showAllBalances() {
        final List<Balance> balances = bank.getAllBalances();
        balances.forEach(System.out::println);
        System.out.println("-----");
    }

    public void showAllBalancesForUser(String userId) {
        User user = userDatabase.lookup(userId);
        final List<Balance> balances = bank.getBalancesForUser(user);
        balances.forEach(System.out::println);
        System.out.println("-----");
    }

    public void showHistoryForUser(String userId) {
        User user = userDatabase.lookup(userId);
        final List<Balance> balances = bank.getHistoryForUser(user);
        balances.forEach(System.out::println);
        System.out.println("-----");
    }
}
