package io.github.jayennn.BlockchainVoting.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "candidates")
public class Candidate {
    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(columnDefinition = "binary(16)")
    private UUID uuid;

    @Column(length = 100)
    private String name;

    private String vission;

    private String mission;

    @ManyToOne
    @JoinColumn(name = "election_uuid")
    private Election election;

    public Candidate(){}

    public Candidate(String name){
        this.name = name;
        this.mission = "misi";
        this.vission = "visi";
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getMission() {
        return mission;
    }

    public String getVission() {
        return vission;
    }

    public Election getElection() {
        return election;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public void setVission(String vission) {
        this.vission = vission;
    }

    public void setElection(Election election) {
        this.election = election;
    }
}

