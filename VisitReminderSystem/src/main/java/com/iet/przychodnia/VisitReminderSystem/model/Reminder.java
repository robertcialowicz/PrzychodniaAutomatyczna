package com.iet.przychodnia.VisitReminderSystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

public class Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final UUID id;
    private final UUID patientID;
    private final UUID doctorID;
    private final UUID visitID;

    public Reminder() {
        this.id = null;
        this.patientID = null;
        this.doctorID = null;
        this.visitID = null;
    }

    public Reminder(@JsonProperty("id") UUID id,
                    @JsonProperty("patientID") UUID patientID,
                    @JsonProperty("doctorID") UUID doctorID,
                    @JsonProperty("date") UUID visitID) {
        this.id = id;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.visitID = visitID;
    }

    @Override
    public String toString() {
        return "Reminder{" +
                "id=" + id +
                ", patientID=" + patientID +
                ", doctorID=" + doctorID +
                ", datetime='" + visitID + '\'' +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public UUID getPatientID() {
        return patientID;
    }

    public UUID getDoctorID() {
        return doctorID;
    }

    public UUID getDatetime() {
        return visitID;
    }
}
