package io.github.jayennn.BlockchainVoting.seeder;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

import io.github.jayennn.BlockchainVoting.entity.Election;
import io.github.jayennn.BlockchainVoting.utils.JpaManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class ElectionSeeder {

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
      System.out.println("Seeding elections...");

      Election election1 = new Election();
      election1.setTitle("Presidential Election 2024");
      election1.setDateStart(LocalDate.of(2024, 1, 1));
      election1.setDateEnd(LocalDate.of(2024, 12, 31));
      // election1.setDateEnd("2024-12-31");
      election1.setActive(false);

      em.persist(election1);

      System.out.println("Elections seeded successfully.");
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