package io.github.jayennn.BlockchainVoting.database;

import io.github.jayennn.BlockchainVoting.entity.Gender;
import io.github.jayennn.BlockchainVoting.entity.Role;
import io.github.jayennn.BlockchainVoting.entity.User;
import io.github.jayennn.BlockchainVoting.entity.Voter;
import io.github.jayennn.BlockchainVoting.utils.ConfigManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.util.Properties;

public class DatabaseTest {
    @Test
    public void initiateData(){
        Properties properties = ConfigManager.getInstance().getProperties();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("main-pu",properties);

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Voter voter_0 = new Voter("11240000");

        voter_0.setName("Rojo Kasino");
        voter_0.setGender(Gender.MALE);
        voter_0.setDateOfBirth(LocalDate.of(2006,8,31));


        User user_1 = new User(Role.USER,"bukabuka");
        user_1.setVoter(voter_0);
        user_1.setUsername("rojo-soprano");
        user_1.setPassword("testtest");
        em.persist(user_1);
        em.getTransaction().commit();

    }





}
