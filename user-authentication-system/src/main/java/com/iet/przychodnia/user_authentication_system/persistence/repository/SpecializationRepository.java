package com.iet.przychodnia.user_authentication_system.persistence.repository;

import com.iet.przychodnia.user_authentication_system.persistence.model.DoctorSpecialization;
import com.iet.przychodnia.user_authentication_system.persistence.model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, UUID> {
}
