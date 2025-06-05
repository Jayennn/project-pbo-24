package io.github.jayennn.BlockchainVoting.utils;

import org.flywaydb.core.Flyway;

public class FlywayUtil {
    private FlywayUtil(){}

    public static void migrate(){
        ConfigManager config = ConfigManager.getInstance();
        String url = config.get("db.url");
        String user = config.get("db.user");
        String password = config.get("db.password");

        Flyway flyway = Flyway.configure().dataSource(url,user,password).load();
        flyway.migrate();
    }
}
