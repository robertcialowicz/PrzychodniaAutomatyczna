package com.iet.przychodnia.user_authentication_system.config;

import com.iet.przychodnia.user_authentication_system.authorization.AuthorizationRole;
import lombok.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Value
public class AccountDetails implements UserDetails {

	UUID userId;
	String username;
	String password;
	List<AuthorizationRole> roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream()
			.map(AuthorizationRole::toString)
			.map(String::toLowerCase)
			.map(SimpleGrantedAuthority::new)
			.collect(Collectors.toList());
	}

	public UUID getUserId() {
		return userId;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}