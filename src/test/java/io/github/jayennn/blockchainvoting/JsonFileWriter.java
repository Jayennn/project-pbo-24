package io.github.jayennn.blockchainvoting;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.github.jayennn.blockchainvoting.model.Person;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonFileWriter {
    public static void main(String[] args) throws IOException {
        List<Person> people = new ArrayList<>();

        List<String> names = List.of("Alice", "Bob", "Charlie");
        List<Integer> ages = List.of(25, 30, 35);

        for (int i = 0; i < names.size(); i++) {
            people.add(new Person(names.get(i), ages.get(i)));
        }

        String filePath = "person.json";
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            Path path = Paths.get(filePath);
            mapper.writeValue(path.toFile(), people);

            System.out.println("Successfully wrote to: " + path.toAbsolutePath());

        } catch (IOException e){
            System.err.println("Error writing JSON file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
