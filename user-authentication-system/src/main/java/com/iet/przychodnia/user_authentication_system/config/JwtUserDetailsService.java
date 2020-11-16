package com.iet.przychodnia.user_authentication_system.config;

import com.iet.przychodnia.user_authentication_system.persistence.model.Account;
import com.iet.przychodnia.user_authentication_system.persistence.repository.AccountRepository;
import lombok.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Value
public class JwtUserDetailsService implements UserDetailsService {

    PasswordEncoder passwordEncoder;
    AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(s);
        if(account == null) {
            throw new UsernameNotFoundException("User not found with username: " + s);
        } else {
            return new org.springframework.security.core.userdetails.User(account.getUsername(), account.getPassword(), new ArrayList<>());
        }
    }
}
