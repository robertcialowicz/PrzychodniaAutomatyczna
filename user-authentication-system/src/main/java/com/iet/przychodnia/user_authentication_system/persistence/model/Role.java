package com.iet.przychodnia.user_authentication_system.persistence.model;

import com.iet.przychodnia.user_authentication_system.authorization.AuthorizationRole;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@ToString(exclude = "account")
public class Role {

	@Id
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;

	@NotBlank
	private String role;

	public Role(UUID id, Account account, AuthorizationRole role) {
		this.id = id;
		this.account = account;
		this.role = role.toString().toLowerCase();
	}

	public AuthorizationRole getRole() {
		return AuthorizationRole.valueOf(role.toUpperCase());
	}

	public void setRole(AuthorizationRole role) {
		this.role = role.toString().toLowerCase();
	}
}
