package com.splitwise.models;

public class Debt {
    private final User debtOwner;
    private final Double amount;

    public Debt(User debtOwner, Double amount) {
        this.debtOwner = debtOwner;
        this.amount = amount;
    }

    public User getDebtOwner() {
        return debtOwner;
    }

    public Double getAmount() {
        return amount;
    }
}
