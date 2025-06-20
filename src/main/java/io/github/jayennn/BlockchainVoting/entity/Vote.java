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

  public void setVoter(Voter voter) {
    this.voter = voter;
  }

  public void setId(VoteId id) {
    this.id = id;
  }

  public void setCandidate(Candidate candidate) {
    this.candidate = candidate;
  }

  public void setElection(Election election) {
    this.election = election;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }
}
