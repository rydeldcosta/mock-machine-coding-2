package com.splitwise;

import com.splitwise.models.User;

public class Main {
    /**
     * Requirements
     * 1. Take a txn as input, split among users and update balance of each user
     *
     * User: Each user should have a userId, name, email, mobile number.
     * ExpenseCreator: Could either be EQUAL, EXACT or PERCENT
     * Users can add any amount, select any type of expense and split with any of the available users.
     * The percent and amount provided could have decimals upto two decimal places.
     * In case of percent, you need to verify if the total sum of percentage shares is 100 or not.
     * In case of exact, you need to verify if the total sum of shares is equal to the total amount or not.
     * The application should have a capability to show expenses for a single user as well as balances for everyone.
     * When asked to show balances, the application should show balances of a user with all the users where there is a non-zero balance.
     * The amount should be rounded off to two decimal places. Say if User1 paid 100 and amount is split equally among 3 people. Assign 33.34 to first person and 33.33 to others.
     */

    /**
     * Input
     * SHOW
     * SHOW u1
     * EXPENSE u1 1000 4 u1 u2 u3 u4 EQUAL
     * SHOW u4
     * SHOW u1
     * EXPENSE u1 1250 2 u2 u3 EXACT 370 880
     * SHOW
     * EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20
     * SHOW u1
     * SHOW
     * @param args
     */
    public static void main(String[] args) {
        User u1 = new User("u1", "User1", "u1@gmail.com", "12345");
        User u2 = new User("u2", "User2", "u2@gmail.com", "12345");
        User u3 = new User("u3", "User3", "u3@gmail.com", "12345");
        User u4 = new User("u4", "User4", "u4@gmail.com", "12345");
        SplitwiseService service = new SplitwiseService();
        service.registerUser(u1);
        service.registerUser(u2);
        service.registerUser(u3);
        service.registerUser(u4);

        service.executeTransaction("EXPENSE u1 1000 4 u1 u2 u3 u4 EQUAL");
        service.showAllBalancesForUser(u4.getId());
        service.showAllBalancesForUser(u1.getId());

        service.executeTransaction("EXPENSE u1 1250 2 u2 u3 EXACT 370 880");
        service.showAllBalances();;

        service.executeTransaction("EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20");
        service.showAllBalancesForUser("u1");
        service.showAllBalances();

        service.showHistoryForUser("u1");
    }

}
