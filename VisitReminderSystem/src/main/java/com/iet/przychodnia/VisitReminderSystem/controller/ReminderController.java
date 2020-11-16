package com.iet.przychodnia.VisitReminderSystem.controller;

import com.iet.przychodnia.VisitReminderSystem.model.Reminder;
import com.iet.przychodnia.VisitReminderSystem.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
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

    @GetMapping
    public List<Reminder> selectAllRemindersForGivenPatient (UUID patientId){
        return reminderService.selectAllRemindersForGivenUser(patientId);
    }

}
