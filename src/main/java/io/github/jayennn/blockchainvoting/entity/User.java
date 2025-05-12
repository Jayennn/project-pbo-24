package io.github.jayennn.blockchainvoting.entity;

import io.github.jayennn.blockchainvoting.enums.Role;

public abstract class User {
    private final String userId;
    private final String username;
    private final String password;
    private final Role role;

    public User(String userId, String username, String password, Role role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}
