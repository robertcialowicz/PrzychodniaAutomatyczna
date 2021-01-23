package com.iet.przychodnia.user_authentication_system.controller;

import com.iet.przychodnia.user_authentication_system.controller.requests.RegistrationRequest;
import com.iet.przychodnia.user_authentication_system.controller.response.AccountResponse;
import com.iet.przychodnia.user_authentication_system.service.AccountService;
import lombok.Value;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/user")
@Value
public class UserController {

    AccountService accountService;

    @PostMapping(value = "/register")
    public ResponseEntity<Void> addUser(@Valid @NonNull @RequestBody RegistrationRequest user, @RequestHeader(required = false) String Authorization){
        accountService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAllUsers(@RequestHeader(required = false) String Authorization){
        val accounts = accountService.findAllUsers();

        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AccountResponse> getUserById(@PathVariable("id") UUID id, @RequestHeader(required = false) String Authorization){
        val account = accountService.findUserById(id).orElse(null);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") UUID id, @RequestHeader(required = false) String Authorization){
        accountService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody RegistrationRequest user, @RequestHeader(required = false) String Authorization){
        accountService.save(id, user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
