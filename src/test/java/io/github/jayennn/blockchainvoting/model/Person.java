package io.github.jayennn.blockchainvoting.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {
    @JsonProperty("personName")
    private String name;

    @JsonProperty("personAge")
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
