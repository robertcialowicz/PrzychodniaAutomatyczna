package com.iet.przychodnia.BookVisitSystem.service;

import com.iet.przychodnia.BookVisitSystem.model.Visit;
import com.iet.przychodnia.BookVisitSystem.repository.IVisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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

    public int addVisit(Visit visit){
        return visitRepository.insertVisit(visit);
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

    public int updateVisit(UUID id, Visit newVisit){
        return visitRepository.updateVisitById(id, newVisit);
    }

}
