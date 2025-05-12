package io.github.jayennn.BlockchainVoting;

import org.flywaydb.core.Flyway;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello from main");
        Properties props = PropertiesHandler.getProps("flyway.properties");

        String url = props.getProperty("flyway.url");
//        Flyway flyway = Flyway.configure().dataSource()dd
    }
}
