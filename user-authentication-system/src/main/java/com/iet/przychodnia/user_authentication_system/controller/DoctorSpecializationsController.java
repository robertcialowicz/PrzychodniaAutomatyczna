package com.iet.przychodnia.user_authentication_system.controller;

import com.iet.przychodnia.user_authentication_system.controller.requests.RegistrationRequest;
import com.iet.przychodnia.user_authentication_system.controller.requests.SpecializationRequest;
import com.iet.przychodnia.user_authentication_system.controller.response.AccountResponse;
import com.iet.przychodnia.user_authentication_system.controller.response.SpecializationResponse;
import com.iet.przychodnia.user_authentication_system.service.SpecializationsService;
import lombok.Value;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/specializations")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Value
public class DoctorSpecializationsController {

    SpecializationsService specializationsService;

    @GetMapping
    public ResponseEntity<List<SpecializationResponse>> getAllSpecializations(@RequestHeader(required = false) String Authorization){
        val specializations = specializationsService.findAllSpecializations();

        return new ResponseEntity<>(specializations, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Void> addSpecialization(@Valid @NonNull @RequestBody SpecializationRequest specialization, @RequestHeader(required = false) String Authorization){
        specializationsService.save(specialization);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
