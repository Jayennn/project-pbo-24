package io.github.jayennn.BlockchainVoting.entity;

import jakarta.persistence.*;

import java.util.UUID;

//@Entity
public class Users {
    @Id
    @Column(columnDefinition = "binary(16")
    private UUID uuid;

    @Column(length = 31)
    private String username;

    @Column(length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(length = 10)
    private String voter_id;
}

