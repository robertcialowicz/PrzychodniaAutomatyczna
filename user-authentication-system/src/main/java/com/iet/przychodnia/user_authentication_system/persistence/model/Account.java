package com.iet.przychodnia.user_authentication_system.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    private UUID id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String email;

    private boolean isDoctor;

    @OneToMany(mappedBy = "account")
    private List<Role> roles;

    public Account(UUID id, @NotBlank String username, @NotBlank String password, @NotBlank String email, boolean isDoctor) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.isDoctor = isDoctor;
        this.roles = Collections.emptyList();
    }
}
