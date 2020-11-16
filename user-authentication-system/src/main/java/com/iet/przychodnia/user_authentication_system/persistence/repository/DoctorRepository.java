package com.iet.przychodnia.user_authentication_system.persistence.repository;

import com.iet.przychodnia.user_authentication_system.persistence.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, UUID> {

    Optional<Doctor> findByAccountId(UUID accountId);
    void deleteByAccountId(UUID accountId);
}
