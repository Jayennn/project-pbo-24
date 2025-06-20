package io.github.jayennn.BlockchainVoting.controller.admin;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import io.github.jayennn.BlockchainVoting.entity.Election;
import io.github.jayennn.BlockchainVoting.utils.JpaManager;
import jakarta.persistence.EntityManager;

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
}