package com.iet.przychodnia.VisitReminderSystem.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iet.przychodnia.VisitReminderSystem.model.Reminder;
import com.iet.przychodnia.VisitReminderSystem.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/reminder")
public class ReminderController {

    private final ReminderService reminderService;

    @Autowired
    public ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;
    }

    @PostMapping
    public Reminder insertReminder(@RequestBody Reminder reminder){
        return reminderService.insertReminder(reminder);
    }

    @GetMapping(path = "/patient/{id}")
    public List<Reminder> selectAllRemindersForGivenPatient(@PathVariable("id") UUID patientId){
        return reminderService.selectAllRemindersForGivenUser(patientId);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteReminderById(@PathVariable("id") UUID id){
        if (reminderService.deleteReminderById(id) == 0){
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping(path = "/visit/{id}")
    public ResponseEntity deleteReminderByVisitId(@PathVariable("id") UUID id){
        if (reminderService.deleteReminderByVisitId(id) == 0){
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

}
