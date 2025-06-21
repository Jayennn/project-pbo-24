package io.github.jayennn.BlockchainVoting.controller.admin;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import io.github.jayennn.BlockchainVoting.entity.Candidate;
import io.github.jayennn.BlockchainVoting.entity.Election;
import io.github.jayennn.BlockchainVoting.utils.JpaManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class CandidateController {

  public CandidateController() {
    // Constructor logic can be added here if needed
    System.out.println("CandidateController initialized");
  }

  public List<Candidate> getCandidates() {
    EntityManager em = JpaManager.getInstance().getEM();
    EntityTransaction tx = em.getTransaction();

    try {
      tx.begin();
      System.out.println("Retrieving candidates...");

      List<Candidate> candidate = em.createQuery("SELECT c FROM Candidate c", Candidate.class)
          .getResultList();

      if (candidate.isEmpty()) {
        System.out.println("No candidates found.");
        return Collections.emptyList(); // Return an empty list if no candidates are found
      }

      System.out.println("Candidates retrieved successfully.");
      tx.commit();

      return candidate;
    } catch (Exception e) {
      System.err.println("Error retrieving candidates: " + e.getMessage());
      if (tx.isActive()) {
        tx.rollback();
      }
      return Collections.emptyList(); // Return an empty list if an error occurs
    } finally {
      em.close();
    }

  }

  public Candidate getCandidateById(String id) {
    EntityManager em = JpaManager.getInstance().getEM();
    EntityTransaction tx = em.getTransaction();

    try {
      tx.begin();

      System.out.println("Retrieving candidate by ID: " + id);
      Candidate candidate = em.find(Candidate.class, id);

      if (candidate == null) {
        System.out.println("Candidate not found with ID: " + id);
        return null;
      }

      System.out.println("Candidate retrieved successfully.");
      tx.commit();

      return candidate;
    } catch (Exception e) {
      System.err.println("Error retrieving candidate by ID: " + e.getMessage());
      if (tx.isActive()) {
        tx.rollback();
      }
      return null; // Return null if an error occurs
    } finally {
      em.close();
    }
  }

  public void addCandidate(String name, String mission, String vission, Election election) {
    EntityManager em = JpaManager.getInstance().getEM();
    EntityTransaction tx = em.getTransaction();

    Candidate candidate = new Candidate(name);
    candidate.setMission(mission);
    candidate.setVission(vission);
    candidate.setElection(election);

    try {
      tx.begin();
      System.out.println("Adding new candidate: " + candidate.getName());

      em.persist(candidate);
      tx.commit();

      System.out.println("Candidate added successfully.");
    } catch (Exception e) {
      System.err.println("Error adding candidate: " + e.getMessage());
      if (tx.isActive()) {
        tx.rollback();
      }
    } finally {
      em.close();
    }
  }

  public void editCandidate(UUID id, String name, String mission, String vission, Election election) {
    EntityManager em = JpaManager.getInstance().getEM();
    EntityTransaction tx = em.getTransaction();

    try {
      tx.begin();
      System.out.println("Updating candidate with ID: " + id);

      Candidate existingCandidate = em.find(Candidate.class, id);
      if (existingCandidate != null) {
        existingCandidate.setName(name);
        existingCandidate.setMission(mission);
        existingCandidate.setVission(vission);
        existingCandidate.setElection(election);
        em.merge(existingCandidate);
        System.out.println("Candidate updated successfully.");
      } else {
        System.out.println("Candidate not found with ID: " + id);
      }

      tx.commit();
    } catch (Exception e) {
      System.err.println("Error updating candidate: " + e.getMessage());
      if (tx.isActive()) {
        tx.rollback();
      }
    } finally {
      em.close();
    }
  }

  public void deleteCandidate(UUID id) {
    EntityManager em = JpaManager.getInstance().getEM();
    EntityTransaction tx = em.getTransaction();

    try {
      tx.begin();
      System.out.println("Deleting candidate with ID: " + id);

      Candidate candidate = em.find(Candidate.class, id);
      if (candidate != null) {
        em.remove(candidate);
        System.out.println("Candidate deleted successfully.");
      } else {
        System.out.println("Candidate not found with ID: " + id);
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
}
