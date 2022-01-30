package com.splitwise.datastore;

import com.splitwise.models.Balance;
import com.splitwise.models.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private final Map<User, Map<User, Double>> records = new HashMap<>();
    private final Map<User, List<Balance>> transactionHistory = new HashMap<>();

    public void recordTransaction(User payee, User debtor, Double amount) {
        Map<User, Double> recordsForPayee = records.computeIfAbsent(payee, (user) -> new HashMap<>());
        final Double payeeAmount = recordsForPayee.getOrDefault(debtor, 0d);
        recordsForPayee.put(debtor, amount + payeeAmount);
        Map<User, Double> recordsForDebtor = records.computeIfAbsent(debtor, (user) -> new HashMap<>());
        Double debtorAmount = recordsForDebtor.getOrDefault(payee, 0d);
        recordsForDebtor.put(payee, -amount + debtorAmount);
        final List<Balance> payeeHistory = transactionHistory.computeIfAbsent(payee, (user) -> new ArrayList<>());
        payeeHistory.add(new Balance(payee, debtor, amount));
        final List<Balance> debtorHistory = transactionHistory.computeIfAbsent(debtor, (user) -> new ArrayList<>());
        debtorHistory.add(new Balance(debtor, payee, -amount));
    }

    public List<Balance> getHistoryForUser(User user) {
        return transactionHistory.getOrDefault(user, Collections.emptyList());
    }

    public List<Balance> getBalancesForUser(User user) {
        List<Balance> balances = new ArrayList<>();
        Map<User, Double> recordsForUser = records.getOrDefault(user, Collections.emptyMap());
        recordsForUser.forEach((debtor, amount) -> {
            if (amount > 0) {
                balances.add(new Balance(user, debtor, amount));
            } else if (amount < 0) {
                balances.add(new Balance(debtor, user, -amount));
            }
        });
        return balances;
    }

    public List<Balance> getAllBalances() {
        List<Balance> balances = new ArrayList<>();
        records.forEach((user, recordsForUser) -> {
            recordsForUser.forEach((debtor, amount) -> {
                if (amount > 0) {
                    balances.add(new Balance(user, debtor, amount));
                }
            });
        });
        return balances;

    }
}
