package io.github.jayennn.BlockchainVoting.utils;

import io.github.jayennn.BlockchainVoting.entity.Gender;
import io.github.jayennn.BlockchainVoting.entity.Role;
import io.github.jayennn.BlockchainVoting.entity.User;
import io.github.jayennn.BlockchainVoting.entity.Voter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.util.Properties;

public class JpaManagerTest {
    @Test
    public void initiateData(){
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
        Boolean result = UBcrypt.verifyHash(password,user.getPassword());

        System.out.println(result);

    }





}
