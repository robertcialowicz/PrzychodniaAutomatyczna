package com.iet.przychodnia.user_authentication_system.service;

import com.iet.przychodnia.user_authentication_system.controller.response.AccountResponse;
import com.iet.przychodnia.user_authentication_system.persistence.model.Account;
import com.iet.przychodnia.user_authentication_system.persistence.model.Doctor;
import com.iet.przychodnia.user_authentication_system.persistence.model.DoctorSpecialization;
import com.iet.przychodnia.user_authentication_system.persistence.model.Patient;
import com.iet.przychodnia.user_authentication_system.persistence.repository.AccountRepository;
import com.iet.przychodnia.user_authentication_system.controller.requests.RegistrationRequest;
import com.iet.przychodnia.user_authentication_system.persistence.repository.DoctorRepository;
import com.iet.przychodnia.user_authentication_system.persistence.repository.DoctorSpecializationRepository;
import com.iet.przychodnia.user_authentication_system.persistence.repository.PatientRepository;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.val;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Value
@NonFinal
@Transactional
public class AccountService {

    AccountRepository accountRepository;
    DoctorRepository doctorRepository;
    PatientRepository patientRepository;
    DoctorSpecializationRepository doctorSpecializationRepository;
    PasswordEncoder bcryptEncoder;

    public void save(RegistrationRequest request) {
        val encodedPassword = bcryptEncoder.encode(request.getPassword());

        val account = accountRepository.save(new Account(UUID.randomUUID(), request.getUsername(), encodedPassword, request.getEmail(), request.getDoctorData() != null));

        if (request.getDoctorData() != null) {
            val data = request.getDoctorData();
            val newDoctor = new Doctor(UUID.randomUUID(),
                                       account,
                                       data.getName(),
                                       data.getSurname(),
                                       data.getPesel());

            doctorRepository.save(newDoctor);

            val specializations = request.getDoctorData().getSpecialization();
            specializations.stream()
                           .forEach(s -> doctorSpecializationRepository.save(new DoctorSpecialization(UUID.randomUUID(), newDoctor, s)));
        } else {
            val data = request.getPatientData();

            patientRepository.save(new Patient(UUID.randomUUID(),
                                               account,
                                               data.getName(),
                                               data.getSurname(),
                                               data.getPesel(),
                                               data.getBirthDate()));
        }
    }

    public void save(UUID id, RegistrationRequest request) {
        val encodedPassword = bcryptEncoder.encode(request.getPassword());
        val account = accountRepository.findById(id);

        if (account.isPresent()) {
            val acc = account.get();

            acc.setEmail(request.getEmail());
            acc.setPassword(encodedPassword);
            acc.setUsername(request.getUsername());
            accountRepository.save(new Account(acc.getId(), acc.getUsername(), encodedPassword, acc.getEmail(), acc.isDoctor()));


            if (acc.isDoctor()) {
                val data = doctorRepository.findByAccountId(acc.getId());

                if (data.isPresent()) {
                    val d = data.get();
                    d.setName(request.getDoctorData().getName());
                    d.setSurname(request.getDoctorData().getSurname());
                    d.setPesel(request.getDoctorData().getPesel());
                    doctorRepository.save(new Doctor(d.getId(), acc, d.getName(), d.getSurname(), d.getPesel()));

                    val specializations = request.getDoctorData().getSpecialization();
                    doctorSpecializationRepository.deleteByDoctorId(d.getId());
                    specializations.stream()
                                   .forEach(s -> doctorSpecializationRepository.save(new DoctorSpecialization(UUID.randomUUID(), d, s)));
                }

            } else {
                val data = patientRepository.findByAccountId(acc.getId());
                if (data.isPresent()) {
                    val p = data.get();
                    p.setName(request.getPatientData().getName());
                    p.setSurname(request.getPatientData().getSurname());
                    p.setPesel(request.getPatientData().getPesel());
                    p.setBirthDate(request.getPatientData().getBirthDate());
                    patientRepository.save(new Patient(p.getId(), acc, p.getName(), p.getSurname(), p.getPesel(), p.getBirthDate()));
                }
            }
        }
    }

    public List<AccountResponse> findAllUsers() {
        return accountRepository.findAll()
                                .stream()
                                .map(a -> {
                                    if (a.isDoctor()) {
                                        val data = doctorRepository.findByAccountId(a.getId());

                                        if (data.isPresent()) {
                                            val d = data.get();
                                            val specializations = doctorSpecializationRepository.findByDoctorId(d.getId());
                                            if (specializations.isPresent()) {
                                                val s = specializations.get().stream()
                                                                       .map(v -> v.getSpecializationId())
                                                                       .collect(Collectors.toList());
                                                return AccountResponse.fromAccountReturnDoctor(a, new AccountResponse.DoctorData(d.getName(), d.getSurname(), d.getPesel(), s));
                                            }
                                            return AccountResponse.fromAccountReturnDoctor(a, new AccountResponse.DoctorData(d.getName(), d.getSurname(), d.getPesel(), null));
                                        }
                                        return AccountResponse.fromAccountReturnDoctor(a, null);

                                    } else {
                                        val data = patientRepository.findByAccountId(a.getId());

                                        if (data.isPresent()) {
                                            val p = data.get();
                                            return AccountResponse.fromAccountReturnPatient(a, new AccountResponse.PatientData(p.getName(), p.getSurname(), p.getPesel(), p.getBirthDate()));
                                        }
                                        return AccountResponse.fromAccountReturnPatient(a, null);
                                    }
                                })
                                .collect(Collectors.toList());
    }

    public Optional<AccountResponse> findUserById(UUID id) {
        val account = accountRepository.findById(id);

        return account.map(a -> {
            if (a.isDoctor()) {
                val data = doctorRepository.findByAccountId(a.getId());

                if (data.isPresent()) {
                    val d = data.get();
                    //fixme
                    return AccountResponse.fromAccountReturnDoctor(a, new AccountResponse.DoctorData(d.getName(), d.getSurname(), d.getPesel(), null));
                }

                return AccountResponse.fromAccountReturnDoctor(a, null);
            } else {
                val data = patientRepository.findByAccountId(a.getId());

                if (data.isPresent()) {
                    val p = data.get();
                    return AccountResponse.fromAccountReturnPatient(a, new AccountResponse.PatientData(p.getName(), p.getSurname(), p.getPesel(), p.getBirthDate()));
                }
                return AccountResponse.fromAccountReturnPatient(a, null);
            }
        });
    }

    public void deleteUser(UUID id) {
        val account = accountRepository.findById(id);
        if (account.isPresent()) {
            val a = account.get();
            if (a.isDoctor()) {
                val doctor = doctorRepository.findByAccountId(id);
                if(doctor.isPresent()) {
                    doctorSpecializationRepository.deleteByDoctorId(doctor.get().getId());
                }
                doctorRepository.deleteByAccountId(id);

            } else {
                patientRepository.deleteByAccountId(id);

            }
        }
    }
}
