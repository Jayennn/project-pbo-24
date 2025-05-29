package io.github.jayennn.BlockchainVoting.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @Column(columnDefinition = "binary(16)")
    private UUID uuid;

    @Column(length = 31)
    private String username;

    @Column(length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(length = 10)
    private String voter_id;

    @OneToOne
    @JoinColumn(name = "voter_id",referencedColumnName = "id", nullable = true)
    private Voters voters;
    public Users(){}

    public UUID getUuid() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }
}

