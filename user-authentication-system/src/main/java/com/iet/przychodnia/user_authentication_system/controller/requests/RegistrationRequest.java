package com.iet.przychodnia.user_authentication_system.controller.requests;

import lombok.Value;

import java.time.LocalDate;

/**
 * @Getter - gettery
 * @Setter - settery
 * @EqualsAndHashCode - metody equals i hashcode
 * <p>
 * konstruktory
 * @NoArgsConstructor - konstruktor bez argumentowy
 * @RequiredArgsConstructor - konstruktor dla pol final
 * @AllArgsConstructor - wszystkie pola
 * <p>
 * immutable object
 * @Value - wszystkie pola private i final, konstruktor dla wszystkich pol, same gettery (bez setterow), equals i hashcode
 * <p>
 * entity
 * @Data - nic o modyfikatorach, gettery, settery, equals, hashcode
 */
@Value
public class RegistrationRequest {

    String username;
    String password;
    String email;

    PatientData patientData;
    DoctorData doctorData;

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
        String specialization;
    }
}