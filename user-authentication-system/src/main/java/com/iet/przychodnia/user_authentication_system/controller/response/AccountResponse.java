package com.iet.przychodnia.user_authentication_system.controller.response;
import com.iet.przychodnia.user_authentication_system.controller.requests.RegistrationRequest;
import com.iet.przychodnia.user_authentication_system.persistence.model.Account;
import com.iet.przychodnia.user_authentication_system.persistence.model.Doctor;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
public class AccountResponse {

    UUID id;
    String email;
    String username;

    PatientData patientData;
    DoctorData doctorData;

    public static AccountResponse fromAccountReturnDoctor(Account a, DoctorData doctorData) {
        return new AccountResponse(
                a.getId(),
                a.getEmail(),
                a.getUsername(),
                null,
                doctorData
        );
    }

    public static AccountResponse fromAccountReturnPatient(Account a, PatientData patientData) {
        return new AccountResponse(
                a.getId(),
                a.getEmail(),
                a.getUsername(),
                patientData,
                null
        );
    }

    @Value
    public static class PatientData {

        String name;
        String surname;
        String pesel;
        String birthDate;
    }

    @Value
    public static class DoctorData {

        String name;
        String surname;
        String pesel;
        List<Integer> specialization;
    }
}
