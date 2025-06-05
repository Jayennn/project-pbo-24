package io.github.jayennn.BlockchainVoting.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Properties;

public class JpaManager {
    private static JpaManager instance;
    private final EntityManagerFactory emf;
    private static Properties properties;

    private JpaManager(){
        properties = ConfigManager.getInstance().getProperties();
        emf = Persistence.createEntityManagerFactory("main-pu",properties);
    }

    public static JpaManager getInstance() {
        if (instance == null) {
            instance = new JpaManager();
        }
        return instance;
    }

    public EntityManagerFactory getEMF(){
        return emf;
    }

    public EntityManager getEM(){
        return emf.createEntityManager();
    }

    public void shutdown(){
        emf.close();
    }
}
