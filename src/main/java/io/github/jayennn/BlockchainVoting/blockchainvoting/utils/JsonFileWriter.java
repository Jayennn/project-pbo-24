package io.github.jayennn.BlockchainVoting.blockchainvoting.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.github.jayennn.BlockchainVoting.blockchainvoting.blockchain.Blockchain;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonFileWriter {

    public static void JsonWritter(Blockchain blockchain) {
        String filePath = "blockchain.json";

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            Path path = Paths.get(filePath);
            mapper.writeValue(path.toFile(), blockchain);

            System.out.println("Successfully wrote blockchain with " + blockchain.getChain().size() + " blocks to: " + path.toAbsolutePath());

        } catch (IOException e) {
            System.err.println("Error writing JSON file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
