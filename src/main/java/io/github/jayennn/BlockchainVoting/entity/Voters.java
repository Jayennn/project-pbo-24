package io.github.jayennn.BlockchainVoting.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Voters {
    @Id
    @Column(length = 10)
    private String id;

    @Column(length = 100)
    private String name;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "public_key", columnDefinition = "TEXT")
    private String publicKey;

    public Voters(){}

    public Voters(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Gender getGender() {
        return gender;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

