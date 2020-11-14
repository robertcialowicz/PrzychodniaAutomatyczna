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
    private final String datetime;
    private final String notes;
    private final UUID doctorID;
    private final UUID patientID;
    private final UUID specializationID;
    private final UUID mediacalsID;

    public Visit(){
        this.id = null;
        this.datetime = null;
        this.notes = null;
        this.doctorID = null;
        this.patientID = null;
        this.specializationID = null;
        this. mediacalsID = null;
    }

    public Visit(@JsonProperty("id") UUID id,
                 @JsonProperty("date") String datetime,
                 @JsonProperty("notes") String notes,
                 @JsonProperty("doctorID") UUID doctorID,
                 @JsonProperty("patientID") UUID patientID,
                 @JsonProperty("specializationID") UUID specializationID,
                 @JsonProperty("medicalsID") UUID mediacalsID) {
        this.id = id;
        this.datetime = datetime;
        this.notes = notes;
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.specializationID = specializationID;
        this. mediacalsID = mediacalsID;
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

    public UUID getMediacalsID() {
        return mediacalsID;
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
                ", mediacalsID=" + mediacalsID +
                '}';
    }
}
