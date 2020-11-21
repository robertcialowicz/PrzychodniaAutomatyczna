package com.iet.przychodnia.user_authentication_system.controller;

import com.iet.przychodnia.user_authentication_system.jwt.Jwt;
import com.iet.przychodnia.user_authentication_system.config.JwtUserDetailsService;
import com.iet.przychodnia.user_authentication_system.jwt.RSA;
import com.iet.przychodnia.user_authentication_system.controller.requests.JwtRequest;
import com.iet.przychodnia.user_authentication_system.controller.response.JwtResponse;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        val userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        val token = Jwt.fromUserDetails(userDetails);

        return ResponseEntity.ok(new JwtResponse(token.sign(RSA.PRIVATE_KEY)));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
