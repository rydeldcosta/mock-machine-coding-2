package com.splitwise.models;

public class ExactPair {
    private final User user;
    private final Double amount;

    public ExactPair(User user, Double amount) {
        this.user = user;
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public Double getAmount() {
        return amount;
    }
}
