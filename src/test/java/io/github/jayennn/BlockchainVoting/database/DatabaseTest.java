package io.github.jayennn.BlockchainVoting.database;

import io.github.jayennn.BlockchainVoting.entity.Gender;
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
    public void migrate(){
        ConfigManager config = ConfigManager.getInstance();
        String url = config.get("db.url");
        String user = config.get("db.user");
        String password = config.get("db.password");

        Flyway flyway = Flyway.configure().dataSource(url,user,password).load();
        flyway.migrate();

    }

    @Test
    public void initiateEMF(){
        Properties properties = ConfigManager.getInstance().getProperties();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("main-pu",properties);

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Voter test_1 =  new Voter("11241000");
        test_1.setName("rojo");
        test_1.setDateOfBirth(LocalDate.of(2006,12,12));
        test_1.setGender(Gender.MALE);
        em.persist(test_1);
        em.getTransaction().commit();
    }

}
