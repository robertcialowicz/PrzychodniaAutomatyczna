package com.iet.przychodnia.BookVisitSystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final UUID id;
    @NotBlank
    private final String date;
    private final String notes;

    public Visit(){
        this.id = null;
        this.date = null;
        this.notes = null;
    }

    public Visit(@JsonProperty("id") UUID id,
                 @JsonProperty("date") String date,
                 @JsonProperty("notes") String notes) {
        this.id = id;
        this.date = date;
        this.notes = notes;
    }

    public UUID getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
