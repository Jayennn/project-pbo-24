package io.github.jayennn.BlockchainVoting.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "votes")
public class Vote {
  @EmbeddedId
  private VoteId id;

  @MapsId("electionUUID")
  @ManyToOne
  @JoinColumn(name = "election_uuid", columnDefinition = "binary(16)")
  private Election election;

  @MapsId("voterId")
  @ManyToOne
  @JoinColumn(name = "voter_id",referencedColumnName = "id", columnDefinition = "varchar(10)")
  private Voter voter;

  @ManyToOne
  @JoinColumn(name = "candidate_uuid", columnDefinition = "binary(16)",nullable = false)
  private Candidate candidate;

  @Column(name = "timestamp")
  private LocalDateTime timestamp;
  public Vote(){}

  public Vote(VoteId voteId,Voter voter,Candidate candidate,Election election){
    this.id = voteId;
    this.voter = voter;
    this.candidate = candidate;
    this.election = election;
    this.timestamp = LocalDateTime.now();
  }

  public VoteId getId() {
    return id;
  }

  public Voter getVoter() {
    return voter;
  }

  public Candidate getCandidate() {
    return candidate;
  }

  public Election getElection() {
    return election;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }
}
