package com.iet.przychodnia.UserAuthenticationSystem.repository;

import com.iet.przychodnia.UserAuthenticationSystem.model.Patient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("list")
public class PatientRepositoryAsList implements IPatientRepository{

    private static List<Patient> DB = new ArrayList<>();

    @Override
    public Patient insertPatient(UUID id, Patient patient) {
        DB.add(new Patient(id, patient.getName(), patient.getSurname(), patient.getPesel(), patient.getEmail(), patient.getBirthday()));
        return patient;
    }

    @Override
    public List<Patient> selectAllPatients() {
        return DB;
    }

    @Override
    public Optional<Patient> selectPatientById(UUID id) {
        return DB.stream()
                .filter(patient -> patient.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deletePatientById(UUID id) {
        Optional<Patient> patientMaybe = selectPatientById(id);
        if(patientMaybe.isPresent()){
            DB.remove(patientMaybe.get());
            return 1;
        }
        return 0;
    }

    @Override
    public int updatePatientById(UUID id, Patient patientToUpdate) {
        return selectPatientById(id)
                .map(patient -> {
                    int indexOfPatientToUpdate = DB.indexOf(patient);
                    if(indexOfPatientToUpdate>=0){
                        DB.set(indexOfPatientToUpdate, new Patient(id, patientToUpdate.getName(), patientToUpdate.getSurname(), patientToUpdate.getEmail(), patientToUpdate.getPesel(), patientToUpdate.getBirthday()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}
