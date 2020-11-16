package com.iet.przychodnia.UserAuthenticationSystem.repository;

import com.iet.przychodnia.UserAuthenticationSystem.model.Doctor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IDoctorRepository {

    Doctor insertDoctor(UUID id, Doctor doctor);

    default Doctor insertDoctor(Doctor doctor){
        UUID id = UUID.randomUUID();
        return insertDoctor(id, doctor);
    }

    List<Doctor> selectAllDoctors();

    Optional<Doctor> selectDoctorById(UUID id);

    int deleteDoctorById(UUID id);

    int updateDoctorById(UUID id, Doctor doctor);

}
