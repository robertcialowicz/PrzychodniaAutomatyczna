package com.iet.przychodnia.user_authentication_system.persistence.repository;

import com.iet.przychodnia.user_authentication_system.persistence.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
    Optional<Patient> findByAccountId(UUID accountId);
    void deleteByAccountId(UUID accountId);
}
