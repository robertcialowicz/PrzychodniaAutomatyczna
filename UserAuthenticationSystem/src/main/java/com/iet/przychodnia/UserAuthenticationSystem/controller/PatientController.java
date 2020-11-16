package com.iet.przychodnia.UserAuthenticationSystem.controller;

import com.iet.przychodnia.UserAuthenticationSystem.model.Patient;
import com.iet.przychodnia.UserAuthenticationSystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    @PostMapping
    public Patient addPatient(@Valid @NonNull @RequestBody Patient patient){
        return patientService.addPatient(patient);
    }

    @GetMapping
    public List<Patient> getAllPatients(){
        return patientService.getAllPatients();
    }

    @GetMapping(path = "/{id}")
    public Patient getPatientById(@PathVariable("id") UUID id){
        return patientService.getPatientById(id).orElse(null);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePatientById(@PathVariable("id") UUID id){
        patientService.deletePatient(id);
    }

    @PutMapping(path = "/{id}")
    public void updatePatient(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Patient patient){
        patientService.updatePatient(id, patient);
    }

}
