package com.iet.przychodnia.UserAuthenticationSystem.repository;

import com.iet.przychodnia.UserAuthenticationSystem.model.Patient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPatientRepository {

    Patient insertPatient(UUID id, Patient patient);

    default Patient insertPatient(Patient patient){
        UUID id = UUID.randomUUID();
        return insertPatient(id, patient);
    }

    List<Patient> selectAllPatients();

    Optional<Patient> selectPatientById(UUID id);

    int deletePatientById(UUID id);

    int updatePatientById(UUID id, Patient visit);

}
