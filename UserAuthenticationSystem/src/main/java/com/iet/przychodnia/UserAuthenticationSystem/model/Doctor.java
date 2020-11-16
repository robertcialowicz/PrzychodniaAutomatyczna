package com.iet.przychodnia.UserAuthenticationSystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final UUID id;
    @NotBlank
    private final String name;
    @NotBlank
    private final String surname;
    private final String email;

    public Doctor() {
        this.id = null;
        this.name = null;
        this.surname = null;
        this.email = null;
    }

    public Doctor(@JsonProperty("id") UUID id,
                @JsonProperty("name") String name,
                @JsonProperty("surname") String surname,
                @JsonProperty("email") String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
