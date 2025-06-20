package io.github.jayennn.BlockchainVoting.controller.admin;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import io.github.jayennn.BlockchainVoting.entity.Gender;
import io.github.jayennn.BlockchainVoting.entity.Voter;
import io.github.jayennn.BlockchainVoting.utils.JpaManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class VoterController {
  public VoterController() {
    System.out.println("VoterController initialized");
  }

  public List<Voter> getVoters() {
    EntityManager em = JpaManager.getInstance().getEM();
    try {
      System.out.println("Retrieving voters...");

      List<Voter> voters = em.createQuery(
          "SELECT v FROM Voter v", Voter.class).getResultList();

      if (voters.isEmpty()) {
        System.out.println("No voters found.");
        return Collections.emptyList();
      }

      System.out.println("Successfully retrieved " + voters.size() + " voters");
      return voters;
    } catch (Exception e) {
      System.err.println("Error retrieving voters: " + e.getMessage());
      return Collections.emptyList();
    } finally {
      em.close();
    }
  }

  public void addVoter(String studentId, String name, Gender gender, LocalDate dateofbirth) {
    EntityManager em = JpaManager.getInstance().getEM();
    EntityTransaction tx = em.getTransaction();

    Voter voter = new Voter();
    voter.setId(studentId);
    voter.setName(studentId);
    voter.setGender(gender);
    voter.setDateOfBirth(dateofbirth);

    try {
      tx.begin();
      System.out.println("Adding new voters: " + voter.getName());

      em.persist(voter);
      System.out.println("Voter added successfully");
      tx.commit();
    } catch (Exception e) {
      System.err.println("Error adding voter: " + e.getMessage());
      if (tx.isActive()) {
        tx.rollback();
      }
    } finally {
      em.close();
    }
  }

  public void deleteVoter(String studentId) {
    EntityManager em = JpaManager.getInstance().getEM();
    EntityTransaction tx = em.getTransaction();

    try {
      tx.begin();
      System.out.println("Deleting voter with ID: " + studentId);

      Voter voter = em.find(Voter.class, studentId);
      if (voter != null) {
        em.remove(voter);
        System.out.println("Voter deleted successfully.");
      } else {
        System.out.println("Voter not found with ID: " + studentId);
      }

      tx.commit();
    } catch (Exception e) {
      System.err.println("Error deleting candidate: " + e.getMessage());
      if (tx.isActive()) {
        tx.rollback();
      }
    } finally {
      em.close();
    }
  }

  public void editVoter(String studentId, String name, Gender gender, LocalDate dateofbirth) {
    EntityManager em = JpaManager.getInstance().getEM();
    EntityTransaction tx = em.getTransaction();

    try {
      tx.begin();
      System.out.println("Updating voter with ID: " + studentId);

      Voter existingVoter = em.find(Voter.class, studentId);
      if (existingVoter != null) {
        existingVoter.setName(name);
        existingVoter.setGender(gender);
        existingVoter.setDateOfBirth(dateofbirth);
        em.merge(existingVoter);
        System.out.println("Voter update successfully");
      } else {
        System.out.println("Voter not found with ID: " + studentId);
      }
      tx.commit();
    } catch (Exception e) {
      System.err.println("Error updating voter: " + e.getMessage());
      if (tx.isActive()) {
        tx.rollback();
      }
    } finally {
      em.close();
    }
  }
}
