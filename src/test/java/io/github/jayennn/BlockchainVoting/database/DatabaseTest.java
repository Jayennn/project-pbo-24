package io.github.jayennn.BlockchainVoting.database;

import io.github.jayennn.BlockchainVoting.utils.ConfigManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;


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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-pu",properties);
    }

}
