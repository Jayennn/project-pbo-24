package io.github.jayennn.BlockchainVoting.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "elections")
public class Election {
  @Id
  @GeneratedValue(generator = "uuid2")
  @Column(columnDefinition = "binary(16)")
  private UUID uuid;

  @Column(length = 63)
  private String title;

  @Column(name = "date_start")
  private LocalDate dateStart;

  @Column(name = "date_end")
  private LocalDate dateEnd;

  @Column(name = "is_active")
  private boolean isActive;

  @OneToMany(mappedBy = "election", cascade = CascadeType.ALL)
  private List<Candidate> candidateList = new ArrayList<>();

  public Election() {
  }

  public UUID getUUID() {
    return uuid;
  }

  public String getTitle() {
    return title;
  }

  public LocalDate getDateStart() {
    return dateStart;
  }

  public LocalDate getDateEnd() {
    return dateEnd;
  }

  public List<Candidate> getCandidateList() {
    return candidateList;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDateStart(LocalDate dateStart) {
    this.dateStart = dateStart;
  }

  public void setDateEnd(LocalDate dateEnd) {
    this.dateEnd = dateEnd;
  }

  @Override
  public String toString() {
    return title;
  }
}
