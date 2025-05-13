package io.github.jayennn.BlockchainVoting.database;

import io.github.jayennn.BlockchainVoting.utils.ConfigManager;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;


import java.util.Properties;

public class DatabaseTest {
    @Test
    public void migrate(){
        ConfigManager config = ConfigManager.getInstance();
        String url = config.get("flyway.url");
        String user = config.get("flyway.user");
        String password = config.get("flyway.password");

        Flyway flyway = Flyway.configure().dataSource(url,user,password).load();
        flyway.migrate();

    }
}
