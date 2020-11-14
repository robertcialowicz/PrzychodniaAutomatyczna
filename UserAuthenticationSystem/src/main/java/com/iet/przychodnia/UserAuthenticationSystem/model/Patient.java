package com.iet.przychodnia.UserAuthenticationSystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final UUID id;
    @NotBlank
    private final String name;
    @NotBlank
    private final String surname;
    private final String pesel;
    private final String email;
    private final String birthday;

    public Patient() {
        this.id = null;
        this.name = null;
        this.surname = null;
        this.pesel = null;
        this.email = null;
        this.birthday = null;
    }
    public Patient(@JsonProperty("id") UUID id,
                @JsonProperty("name") String name,
                @JsonProperty("surname") String surname,
                @JsonProperty("pesel") String pesel,
                @JsonProperty("email") String email,
                @JsonProperty("birthday") String birthday) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.email = email;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", pesel='" + pesel + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
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

    public String getPesel() {
        return pesel;
    }

    public String getBirthday() {
        return birthday;
    }
}
