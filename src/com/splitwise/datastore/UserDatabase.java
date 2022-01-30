package com.splitwise.datastore;

import com.splitwise.models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserDatabase {
    /**
     * UserId -> User
     */
    Map<String, User> userMap = new HashMap<>();

    public void register(User user) {
        userMap.put(user.getId(), user);
    }

    public User lookup(String userId) {
        return Optional.ofNullable(userMap.get(userId))
                .orElseThrow(() -> new IllegalArgumentException("UserId not found: " + userId));
    }
}
