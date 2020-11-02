package com.iet.przychodnia.BookVisitSystem.controller;

import com.iet.przychodnia.BookVisitSystem.model.Visit;
import com.iet.przychodnia.BookVisitSystem.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void addVisit(@Valid @NonNull @RequestBody Visit visit){
        visitService.addVisit(visit);
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
    public void deleteVisitById(@PathVariable("id") UUID id){
        visitService.deleteVisit(id);
    }

    @PutMapping(path = "/{id}")
    public void updateVisit(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Visit visitToUpdate){
        visitService.updateVisit(id, visitToUpdate);
    }
}