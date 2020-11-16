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
    private final UUID doctorId;
    private final List<UUID> medicalIds;

    public Receipt() {
        this.id = null;
        this.patientId = null;
        this.doctorId = null;
        this.medicalIds = null;
    }

    public Receipt(@JsonProperty("id") UUID id,
                   @JsonProperty("patientId") UUID patientId,
                   @JsonProperty("doctorId") UUID doctorId,
                   @JsonProperty("medicalIds") List<UUID> medicalIds) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.medicalIds = medicalIds;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                ", medicalIds=" + medicalIds +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public UUID getPatientId() {
        return patientId;
    }

    public UUID getDoctorId() {
        return doctorId;
    }

    public List<UUID> getMedicalIds() {
        return medicalIds;
    }
}
