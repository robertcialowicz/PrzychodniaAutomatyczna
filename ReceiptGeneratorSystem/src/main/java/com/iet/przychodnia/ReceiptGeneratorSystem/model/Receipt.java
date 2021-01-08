package com.iet.przychodnia.ReceiptGeneratorSystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import java.util.UUID;

public class Receipt {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private final UUID id;
    private final UUID patientId;
    private final UUID visitId;
    private final UUID medicalId;

    public Receipt() {
        this.id = null;
        this.patientId = null;
        this.visitId = null;
        this.medicalId = null;
    }

    public Receipt(@JsonProperty("id") UUID id,
                   @JsonProperty("patientId") UUID patientId,
                   @JsonProperty("visitId") UUID visitId,
                   @JsonProperty("medicalsId") UUID medicalId) {
        this.id = id;
        this.patientId = patientId;
        this.visitId = visitId;
        this.medicalId = medicalId;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", visitId=" + visitId +
                ", medicalId=" + medicalId +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public UUID getPatientId() {
        return patientId;
    }

    public UUID getVisitId() {
        return visitId;
    }

    public UUID getMedicalIds() {
        return medicalId;
    }
}
