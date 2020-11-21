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
        visitObjectJSON.put("patientID", addedVisit.getPatientID().toString());
        visitObjectJSON.put("doctorID", addedVisit.getDoctorID().toString());
        visitObjectJSON.put("visitID", addedVisit.getId().toString());
        visitObjectJSON.put("datetime", addedVisit.getDatetime());
        HttpEntity<String> request = new HttpEntity<String>(visitObjectJSON.toString(), httpHeaders);
        try{
            //release
            restTemplate.postForEntity("http://reminderssystem:8080/api/reminder", request, Void.class);
            //debug
            //restTemplate.postForEntity("http://localhost:8082/api/reminder", request, Void.class);
        } catch (Exception e){
            e.getMessage();
        }

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
        visitObjectJSON.put("patientId", updatedVisit.getPatientID().toString());
        visitObjectJSON.put("doctorId", updatedVisit.getDoctorID().toString());
        visitObjectJSON.put("medicalsId", updatedVisit.getMediacalsID().toString());
        HttpEntity<String> request = new HttpEntity<String>(visitObjectJSON.toString(), httpHeaders);
        try{
            //release
            restTemplate.postForEntity("http://receiptssystem:8081/api/receipt", request, Void.class);
            //debug
            //restTemplate.postForEntity("http://localhost:8081/api/receipt", request, Void.class);
        } catch (Exception e){
            e.getMessage();
        }
        //TODO ewentualnie zaktualizować adres seriwsu

        return updatedVisit;
    }

    public List<Visit> searchForVisitsInGivenPeriod(String fromDate, String toDate){
        return visitRepository.searchForVisitsInGivenPeriod(fromDate, toDate);
    }

    public List<Visit> searchForVisitsInGivenPeriodForPatient(String fromDate, String toDate, UUID patientId){
        return visitRepository.searchForVisitsInGivenPeriodForPatient(fromDate, toDate, patientId);
    }

    public List<Visit> searchForVisitsInGivenPeriodForDoctor(String fromDate, String toDate, UUID doctorId){
        return visitRepository.searchForVisitsInGivenPeriodForDoctor(fromDate, toDate, doctorId);
    }

    public List<Visit> searchForAvailableVisitsInGivenPeriodBySpecialization(String fromDate, String toDate, UUID specializationId){
        return FindFreeVisits.findFreeVisitsBetweenDatesWhenReservedAreGiven(fromDate,
                toDate,
                visitRepository.searchForVisitsInGivenPeriodBySpecialization(fromDate, toDate, specializationId));
    }

    public List<Visit> searchForAvailableVisitsInGivenPeriodByDoctor(String fromDate, String toDate, UUID doctorId){
        return FindFreeVisits.findFreeVisitsBetweenDatesWhenReservedAreGiven(fromDate,
                toDate,
                visitRepository.searchForVisitsInGivenPeriodForDoctor(fromDate, toDate, doctorId));
    }
}
