package com.iet.przychodnia.BookVisitSystem.repository;

import com.iet.przychodnia.BookVisitSystem.model.Visit;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IVisitRepository {
    Visit save(Visit visit);

    Visit update(Visit visit);

    List<Visit> selectAllVisits();

    Optional<Visit> selectVisitById(UUID id);

    int deleteVisitById(UUID id);

    List<Visit> searchForVisitsInGivenPeriod(String fromDate, String toDate);

    public List<Visit> searchForVisitsInGivenPeriodForPatient(String fromDate, String toDate, UUID patientId);

    public List<Visit> searchForVisitsInGivenPeriodForDoctor(String fromDate, String toDate, UUID doctorId);

    public List<Visit> searchForVisitsInGivenPeriodBySpecialization(String fromDate, String toDate, UUID specializationId);

}
