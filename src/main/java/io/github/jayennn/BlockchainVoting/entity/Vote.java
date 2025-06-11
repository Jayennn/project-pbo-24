package io.github.jayennn.BlockchainVoting.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "votes")
public class Vote {
  @Id
  @Column(columnDefinition = "binary(16)")
  private String election_id;


}
