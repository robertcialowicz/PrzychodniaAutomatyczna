package com.iet.przychodnia.user_authentication_system.config;

import com.iet.przychodnia.user_authentication_system.persistence.model.Account;
import com.iet.przychodnia.user_authentication_system.persistence.model.Role;
import com.iet.przychodnia.user_authentication_system.persistence.repository.AccountRepository;
import lombok.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@Value
public class JwtUserDetailsService implements UserDetailsService {

    AccountRepository accountRepository;

    @Override
    public AccountDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(s);
        if (account == null) {
            throw new UsernameNotFoundException("User not found with username: " + s);
        } else {
            System.out.println(">>> " + account.getRoles());
            return new AccountDetails(account.getId(),
                    account.getUsername(),
                    account.getPassword(),
                    account.getRoles().stream().map(Role::getRole).collect(Collectors.toList())
            );
        }
    }
}
