package io.github.jayennn.BlockchainVoting.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Vote {
  @Id
  @Column(length = 10)
  private String id;
}
