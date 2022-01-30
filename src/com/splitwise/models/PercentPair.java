package com.splitwise.models;

public class PercentPair {
    private final User user;
    private final Double percent;

    public PercentPair(User user, Double percent) {
        this.user = user;
        this.percent = percent;
    }

    public User getUser() {
        return user;
    }

    public Double getPercent() {
        return percent;
    }
}
