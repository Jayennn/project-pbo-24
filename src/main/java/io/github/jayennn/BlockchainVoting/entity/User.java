package io.github.jayennn.BlockchainVoting.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(columnDefinition = "binary(16)")
    private UUID uuid;

    @Column(length = 31)
    private String username;

    @Column(length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(optional = true)
    @JoinColumn(name = "voter_id",referencedColumnName = "id", nullable = true)

    private Voter voter;

    public User(){}

    public User(Role role,String password){
       this.role = role;
       this.password = password;
    }
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

