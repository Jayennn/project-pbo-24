package io.github.jayennn.BlockchainVoting.blockchainvoting.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.github.jayennn.BlockchainVoting.blockchainvoting.blockchain.Blockchain;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonFileWriter {
    private static final Logger log = LogManager.getLogger(JsonFileWriter.class);
    public static void JsonWritter(Blockchain blockchain) {
        String filePath = "blockchain.json";

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            Path path = Paths.get(filePath);
            mapper.writeValue(path.toFile(), blockchain);

            log.info("Successfully wrote blockchain with {} block to: {}", blockchain.getChain().size(), path.toAbsolutePath());
        } catch (IOException e) {
            log.error("Error writing JSON file: {}", e.getMessage());
            e.printStackTrace();
        }
    }
}
