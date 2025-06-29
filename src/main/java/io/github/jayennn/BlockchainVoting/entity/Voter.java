package io.github.jayennn.BlockchainVoting.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "voters")
public class Voter {
  @Id
  @Column(length = 10)
  private String id;

  @Column(length = 100)
  private String name;

  @Column(name = "date_of_birth")
  private LocalDate dateOfBirth;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  public Voter() {
  }

  public Voter(String id) {
    this.id = id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

}
