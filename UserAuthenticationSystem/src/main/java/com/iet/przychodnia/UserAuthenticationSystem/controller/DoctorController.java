package com.iet.przychodnia.UserAuthenticationSystem.controller;

import com.iet.przychodnia.UserAuthenticationSystem.model.Doctor;
import com.iet.przychodnia.UserAuthenticationSystem.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService){
        this.doctorService = doctorService;
    }

    @PostMapping
    public Doctor addDoctor(@Valid @NonNull @RequestBody Doctor doctor){
        return doctorService.addDoctor(doctor);
    }

    @GetMapping
    public List<Doctor> getAllDoctors(){
        return doctorService.getAllDoctors();
    }

    @GetMapping(path = "/{id}")
    public Doctor getDoctorById(@PathVariable("id") UUID id){
        return doctorService.getDoctorById(id).orElse(null);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteDoctorById(@PathVariable("id") UUID id){
        doctorService.deleteDoctor(id);
    }

    @PutMapping(path = "/{id}")
    public void updateDoctor(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Doctor doctor){
        doctorService.updateDoctor(id, doctor);
    }

}
