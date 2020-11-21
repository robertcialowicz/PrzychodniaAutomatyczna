package com.iet.przychodnia.ReceiptGeneratorSystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

public class Medical {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final UUID id;
    private final String name;

    public Medical(){
        this.id = null;
        this.name = null;
    }

    public Medical(@JsonProperty("id") UUID id,
                   @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Medical{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
