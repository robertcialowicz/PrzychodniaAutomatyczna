package com.iet.przychodnia.BookVisitSystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final UUID id;
    private final String datetime;
    private final String notes;
    private final UUID doctorID;
    private final UUID patientID;
    private final UUID specializationID;
    private final String medicalsID;

    public Visit(){
        this.id = null;
        this.datetime = null;
        this.notes = null;
        this.doctorID = null;
        this.patientID = null;
        this.specializationID = null;
        this.medicalsID = null;
    }

    public Visit(@JsonProperty("id") UUID id,
                 @JsonProperty("date") String datetime,
                 @JsonProperty("notes") String notes,
                 @JsonProperty("doctorID") UUID doctorID,
                 @JsonProperty("patientID") UUID patientID,
                 @JsonProperty("specializationID") UUID specializationID,
                 @JsonProperty("medicalsID") String medicalsID) {
        this.id = id;
        this.datetime = datetime;
        this.notes = notes;
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.specializationID = specializationID;
        this.medicalsID = medicalsID;
    }

    public UUID getId() {
        return id;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getNotes() {
        return notes;
    }

    public UUID getDoctorID() {
        return doctorID;
    }

    public UUID getPatientID() {
        return patientID;
    }

    public UUID getSpecializationID() {
        return specializationID;
    }

    public String getMedicalsID() {
        return medicalsID;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", datetime='" + datetime + '\'' +
                ", notes='" + notes + '\'' +
                ", doctorID=" + doctorID +
                ", patientID=" + patientID +
                ", specializationID=" + specializationID +
                ", medicalsID=" + medicalsID +
                '}';
    }
}