package io.github.jayennn.BlockchainVoting.blockchainvoting.entity;

import io.github.jayennn.BlockchainVoting.blockchainvoting.enums.Role;
public class Admin extends User {
    private String name;
    public Admin(String userId, String username, String password, Role role) {
        super(userId, username, password, role);
    }


}
