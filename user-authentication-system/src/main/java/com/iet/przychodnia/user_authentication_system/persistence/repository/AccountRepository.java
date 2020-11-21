package com.iet.przychodnia.user_authentication_system.persistence.repository;

import com.iet.przychodnia.user_authentication_system.persistence.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    Account findByUsername(String username);
}
