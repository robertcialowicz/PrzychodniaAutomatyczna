package com.iet.przychodnia.user_authentication_system.persistence.repository;

import com.iet.przychodnia.user_authentication_system.persistence.model.Doctor;
import com.iet.przychodnia.user_authentication_system.persistence.model.DoctorSpecialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DoctorSpecializationRepository extends JpaRepository<DoctorSpecialization, UUID> {
    Optional<List<DoctorSpecialization>> findByDoctorId(UUID doctorId);
    void deleteByDoctorId(UUID doctorId);
}
