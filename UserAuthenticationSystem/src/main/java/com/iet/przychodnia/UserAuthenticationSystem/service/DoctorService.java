package com.iet.przychodnia.UserAuthenticationSystem.service;

import com.iet.przychodnia.UserAuthenticationSystem.model.Doctor;
import com.iet.przychodnia.UserAuthenticationSystem.repository.IDoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DoctorService {

    private final IDoctorRepository doctorRepository;

    @Autowired
    public DoctorService(@Qualifier("MSSqlDoctor") IDoctorRepository doctorRepository){
        this.doctorRepository = doctorRepository;
    }

    public Doctor addDoctor(Doctor doctor){
        return doctorRepository.insertDoctor(doctor);
    }

    public List<Doctor> getAllDoctors(){
        return doctorRepository.selectAllDoctors();
    }

    public Optional<Doctor> getDoctorById(UUID id){
        return doctorRepository.selectDoctorById(id);
    }

    public int deleteDoctor (UUID id){
        return doctorRepository.deleteDoctorById(id);
    }

    public int updateDoctor (UUID id, Doctor newDoctor){
        return doctorRepository.updateDoctorById(id, newDoctor);
    }
}
