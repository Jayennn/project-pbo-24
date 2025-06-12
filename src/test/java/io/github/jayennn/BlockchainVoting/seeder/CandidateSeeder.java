package io.github.jayennn.BlockchainVoting.seeder;

import org.junit.Test;

import io.github.jayennn.BlockchainVoting.entity.Candidate;
import io.github.jayennn.BlockchainVoting.utils.JpaManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class CandidateSeeder {

  @Test
  public void seed() {
    EntityManager em = JpaManager.getInstance().getEM();

    /*
     * 
     * Transaction is for managing the database transaction.
     * It ensures that all operations within the transaction are completed
     * successfully
     * before committing the changes to the database.
     * If any operation fails, the transaction can be rolled back to maintain data
     * integrity.
     */
    EntityTransaction tx = em.getTransaction();

    try {
      tx.begin();
      System.err.println("Seeding candidates...");

      Candidate candidate1 = new Candidate("Candidate 1");
      candidate1.setMission("Mission 1");
      candidate1.setVission("Vision 1");

      Candidate candidate2 = new Candidate("Candidate 2");
      candidate2.setMission("Mission 2");
      candidate2.setVission("Vision 2");

      em.persist(candidate1);
      em.persist(candidate2);

      System.err.println("Candidates seeded successfully.");
      tx.commit();
    } catch (Exception e) {
      if (tx.isActive()) {
        tx.rollback();
      }

      e.printStackTrace();
    } finally {
      em.close();
    }
  }
}
