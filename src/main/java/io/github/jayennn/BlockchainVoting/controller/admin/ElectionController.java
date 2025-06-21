package io.github.jayennn.BlockchainVoting.controller.admin;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import io.github.jayennn.BlockchainVoting.entity.Election;
import io.github.jayennn.BlockchainVoting.utils.JpaManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class ElectionController {
  public ElectionController() {
    System.out.println("ElectionController initialized");
  }

  public List<Election> getElections() {
    EntityManager em = JpaManager.getInstance().getEM();
    try {
      System.out.println("Retrieving elections...");

      List<Election> elections = em.createQuery(
          "SELECT e FROM Election e", Election.class)
          .getResultList();

      if (elections.isEmpty()) {
        System.out.println("No elections found.");
        return Collections.emptyList();
      }

      System.out.println("Successfully retrieved " + elections.size() + " elections");
      return elections;
    } catch (Exception e) {
      System.err.println("Error retrieving elections: " + e.getMessage());
      return Collections.emptyList();
    } finally {
      em.close();
    }
  }

  // Additional useful methods
  public List<Election> getActiveElections() {
    EntityManager em = JpaManager.getInstance().getEM();
    try {
      return em.createQuery(
          "SELECT e FROM Election e WHERE e.isActive = true ORDER BY e.startDate DESC",
          Election.class)
          .getResultList();
    } finally {
      em.close();
    }
  }

  public Election getElectionById(UUID uuid) {
    EntityManager em = JpaManager.getInstance().getEM();
    try {
      return em.find(Election.class, uuid);
    } finally {
      em.close();
    }
  }

  public void addElection(String title, LocalDate startDate, LocalDate endDate, boolean status) {
    EntityManager em = JpaManager.getInstance().getEM();
    EntityTransaction tx = em.getTransaction();

    Election election = new Election();
    election.setTitle(title);
    election.setDateStart(startDate);
    election.setDateEnd(endDate);

    try {
      tx.begin();
      System.out.println("Adding new election: " + election.getTitle());

      em.persist(election);
      tx.commit();

      System.out.println("election added successfully.");
    } catch (Exception e) {
      System.err.println("Error adding election: " + e.getMessage());
      if (tx.isActive()) {
        tx.rollback();
      }
    } finally {
      em.close();
    }
  }

  public void deleteElection(UUID id) {
    EntityManager em = JpaManager.getInstance().getEM();
    EntityTransaction tx = em.getTransaction();

    try {
      tx.begin();
      System.out.println("Deleting election with ID: " + id);

      Election election = em.find(Election.class, id);
      if (election != null) {
        em.remove(election);
        System.out.println("Election deleted successfully.");
      } else {
        System.out.println("Election not found with ID: " + id);
      }

      tx.commit();
    } catch (Exception e) {
      System.err.println("Error deleting election: " + e.getMessage());
      if (tx.isActive()) {
        tx.rollback();
      }
    } finally {
      em.close();
    }
  }

  public void editElection(UUID id, String title, LocalDate startDate, LocalDate endDate) {
    EntityManager em = JpaManager.getInstance().getEM();
    EntityTransaction tx = em.getTransaction();

    try {
      tx.begin();
      System.out.println("Updating election with ID: " + id);
      Election existingElection = em.find(Election.class, id);
      if (existingElection != null) {
        existingElection.setTitle(title);
        existingElection.setDateStart(startDate);
        existingElection.setDateEnd(endDate);
        // existingElection.setActive(status);
        em.merge(existingElection);
        System.out.println("Election updated successfully.");
      } else {
        System.out.println("Election not found with ID: " + id);
      }
      tx.commit();
    } catch (Exception e) {
      System.err.println("Error updating election: " + e.getMessage());
      if (tx.isActive()) {
        tx.rollback();
      }
    } finally {
      em.close();
    }
  }

  public void updateStatus(UUID id, boolean status) {
    EntityManager em = JpaManager.getInstance().getEM();
    EntityTransaction tx = em.getTransaction();

    try {
      tx.begin();
      Election existingElection = em.find(Election.class, id);
      if (existingElection == null) {
        System.out.println("Election not found with ID: " + id);
        return;
      }

      existingElection.setActive(status);
      em.merge(existingElection);

      System.out.println("Election updated successfully.");

      tx.commit();
    } catch (Exception e) {
      System.err.println("Error updating Election: " + e.getMessage());
      if (tx.isActive()) {
        tx.rollback();
      }
    } finally {
      em.close();
    }
  }
}