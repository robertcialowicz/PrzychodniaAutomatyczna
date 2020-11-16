package com.iet.przychodnia.UserAuthenticationSystem.service;

import com.iet.przychodnia.UserAuthenticationSystem.model.Patient;
import com.iet.przychodnia.UserAuthenticationSystem.repository.IPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientService {

    private final IPatientRepository patientRepository;

    @Autowired
    public PatientService(@Qualifier("MSSqlPatient") IPatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    public Patient addPatient(Patient patient){
        return patientRepository.insertPatient(patient);
    }

    public List<Patient> getAllPatients(){
        return patientRepository.selectAllPatients();
    }

    public Optional<Patient> getPatientById(UUID id){
        return patientRepository.selectPatientById(id);
    }

    public int deletePatient (UUID id){
        return patientRepository.deletePatientById(id);
    }

    public int updatePatient (UUID id, Patient newPatient){
        return patientRepository.updatePatientById(id, newPatient);
    }
}
