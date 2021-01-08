package com.iet.przychodnia.user_authentication_system.service;

import com.iet.przychodnia.user_authentication_system.controller.requests.RegistrationRequest;
import com.iet.przychodnia.user_authentication_system.controller.requests.SpecializationRequest;
import com.iet.przychodnia.user_authentication_system.controller.response.SpecializationResponse;
import com.iet.przychodnia.user_authentication_system.persistence.model.Specialization;
import com.iet.przychodnia.user_authentication_system.persistence.repository.SpecializationRepository;
import lombok.Value;
import lombok.experimental.NonFinal;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Value
@NonFinal
@Transactional
public class SpecializationsService {

    SpecializationRepository specializationRepository;

    public List<SpecializationResponse> findAllSpecializations() {
        return specializationRepository.findAll()
            .stream()
            .map(a -> new SpecializationResponse(a.getId(), a.getName()))
            .collect(Collectors.toList());
    }

    public void save(SpecializationRequest request) {
        specializationRepository.save(new Specialization(UUID.randomUUID(), request.getName()));
    }
}
