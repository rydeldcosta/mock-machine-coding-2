package com.splitwise.models;

public class Balance {
    private final User payee;
    private final User debtor;
    private final Double amount;

    public Balance(User payee, User debtor, Double amount) {
        this.payee = payee;
        this.debtor = debtor;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "User " + debtor +
                " owes " + payee +
                ", amount=" + amount +
                '.';
    }
}
