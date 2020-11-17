package com.iet.przychodnia.BookVisitSystem.service;

import com.iet.przychodnia.BookVisitSystem.model.Visit;
import com.iet.przychodnia.BookVisitSystem.repository.IVisitRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VisitService {

    private final IVisitRepository visitRepository;

    @Autowired
    public VisitService(@Qualifier("MSSql") IVisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    public Visit addVisit(Visit visit){
        //stworz wizyte w bazie
        Visit addedVisit = visitRepository.insertVisit(visit);

        //wyslij POST do serwisu VisitReminderSystem
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        JSONObject visitObjectJSON = new JSONObject();
        visitObjectJSON.put("patientID", addedVisit.getPatientID());
        visitObjectJSON.put("doctorID", addedVisit.getDoctorID());
        visitObjectJSON.put("visitID", addedVisit.getId());
        HttpEntity<String> request = new HttpEntity<String>(visitObjectJSON.toString(), httpHeaders);
        restTemplate.postForEntity("http://remindersystem:8080", request, String.class);
        //TODO ewentualnie zaktualizować adres seriwsu

        return addedVisit;
    }

    public List<Visit> getAllPeople(){
        return visitRepository.selectAllVisits();
    }

    public Optional<Visit> getVisitById(UUID id){
        return visitRepository.selectVisitById(id);
    }

    public int deleteVisit(UUID id) {
        return visitRepository.deleteVisitById(id);
    }

    public Visit updateVisit(UUID id, Visit newVisit){
        //zaktualizuj wizyte w bazie
        Visit updatedVisit = visitRepository.updateVisitById(id, newVisit);

        //wyslij POST do serwisu ReceiptGeneratorSystem
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        JSONObject visitObjectJSON = new JSONObject();
        visitObjectJSON.put("patientID", updatedVisit.getPatientID());
        visitObjectJSON.put("doctorID", updatedVisit.getDoctorID());
        visitObjectJSON.put("medicalsID", updatedVisit.getMediacalsID());
        HttpEntity<String> request = new HttpEntity<String>(visitObjectJSON.toString(), httpHeaders);
        restTemplate.postForEntity("http://receiptsystem:8080", request, String.class);
        //TODO ewentualnie zaktualizować adres seriwsu

        return updatedVisit;
    }

    public List<Visit> searchForVisitsInGivenPeriod(String fromDate, String toDate){
        return visitRepository.searchForVisitsInGivenPeriod(fromDate, toDate);
    }

    public List<Visit> searchForVisitsInGivenPeriodForPatient(String fromDate, String toDate, UUID patientId){
        //TODO implement
        return null;
    }

    public List<Visit> searchForVisitsInGivenPeriodForDoctor(String fromDate, String toDate, UUID doctorId){
        //TODO implement
        return null;
    }

    public List<Visit> searchForAvailableVisitsInGivenPeriodBySpecialization(String fromDate, String toDate, UUID specializationId){
        //TODO implement
        return null;
    }

    public List<Visit> searchForAvailableVisitsInGivenPeriodByDoctor(String fromDate, String toDate, UUID doctorId){
        //TODO implement
        return null;
    }
}
