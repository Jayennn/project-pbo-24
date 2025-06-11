package io.github.jayennn.BlockchainVoting.utils;

import io.github.jayennn.BlockchainVoting.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;


import java.time.LocalDate;
import java.util.Properties;

public class JpaManagerTest {
    @Test
    public void initiateUserData(){
        EntityManager em = JpaManager.getInstance().getEM();

        em.getTransaction().begin();

        Voter voter_0 = new Voter("11240000");

        voter_0.setName("Rojo Kasino");
        voter_0.setGender(Gender.MALE);
        voter_0.setDateOfBirth(LocalDate.of(2006,8,31));
        em.persist(voter_0);

        User user_1 = new User(Role.USER,"bukabuka");
        user_1.setVoter(voter_0);
        user_1.setUsername("rojo-soprano");

        UpdatableBcrypt UBcrypt = new UpdatableBcrypt(12);
        user_1.setPassword(UBcrypt.hash("1234abcd"));
        em.persist(user_1);

        em.getTransaction().commit();

    }

    @ParameterizedTest(name = "nim: {0},username: {1},password: {2}")
    @CsvSource({
            "11241069, clemont, bukabuka"
    })
    public void createAccount(String nim,String username,String password){
        EntityManager em = JpaManager.getInstance().getEM();

        em.getTransaction().begin();

        Voter voter_0 = new Voter(nim);

        voter_0.setName(username);
        voter_0.setGender(Gender.MALE);
        voter_0.setDateOfBirth(LocalDate.of(2006,8,31));
        em.persist(voter_0);

        User user_1 = new User(Role.USER,password);
        user_1.setVoter(voter_0);
        user_1.setUsername(username);

        em.persist(user_1);

        em.getTransaction().commit();
    }

    @Test
    public void comparePassword(){
        EntityManager em = JpaManager.getInstance().getEM();

        em.getTransaction().begin();


        User user = em.createQuery(
                "SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username","rojo-soprano")
                .getSingleResult();

        String password = "1234abcd";
        UpdatableBcrypt UBcrypt = new UpdatableBcrypt(12);
        boolean result = UBcrypt.verifyHash(password,user.getPassword());

        System.out.println(result);

    }

    @Test
    public void initiateElectionData(){
        EntityManager em = JpaManager.getInstance().getEM();

        em.getTransaction().begin();

        Election election = new Election();

        election.setDateStart(LocalDate.now());
        election.setDateEnd(LocalDate.now().plusDays(7));
        election.setTitle("election");
        election.setActive(true);
        em.persist(election);

        Candidate candidate1 = new Candidate("candidate1");

        candidate1.setElection(election);
        em.persist(candidate1);

        Candidate candidate2 = new Candidate("candidate2");

        candidate2.setElection(election);

        em.persist(candidate2);
        em.getTransaction().commit();
    }



}
