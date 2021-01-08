package com.iet.przychodnia.user_authentication_system.persistence.repository;

import com.iet.przychodnia.user_authentication_system.persistence.model.Patient;
import com.iet.przychodnia.user_authentication_system.persistence.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleEntityRepository extends JpaRepository<Role, UUID> {

    Optional<Role> findByAccountId(UUID accountId);
    void deleteByAccountId(UUID accountId);
}
