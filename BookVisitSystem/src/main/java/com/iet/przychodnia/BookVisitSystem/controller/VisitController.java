package com.iet.przychodnia.BookVisitSystem.controller;

import com.iet.przychodnia.BookVisitSystem.model.Visit;
import com.iet.przychodnia.BookVisitSystem.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/visit")
public class VisitController {
    private final VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @PostMapping
    public Visit addVisit( @RequestBody Visit visit){
        return visitService.addVisit(visit);
    }

    @GetMapping
    public List<Visit> getAllVisits(){
        return visitService.getAllPeople();
    }

    @GetMapping(path = "/{id}")
    public Visit getVisitById(@PathVariable("id") UUID id){
        return visitService.getVisitById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteVisitById(@PathVariable("id") UUID id){
        if (visitService.deleteVisit(id) == 0){
            return new ResponseEntity(HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping(path = "/{id}")
    public Visit updateVisit(@PathVariable("id") UUID id, @NonNull @RequestBody Visit visitToUpdate){
        return visitService.updateVisit(id, visitToUpdate);
    }

    @GetMapping(path="/search/{fromDate}/{toDate}")
    public List<Visit> searchForVisitsInGivenPeriod(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate){
        return visitService.searchForVisitsInGivenPeriod(fromDate, toDate);
    }
}