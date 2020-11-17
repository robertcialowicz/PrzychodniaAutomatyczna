package com.iet.przychodnia.VisitReminderSystem.service;

import com.iet.przychodnia.VisitReminderSystem.model.Reminder;
import com.iet.przychodnia.VisitReminderSystem.repository.IReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReminderService {

    private final IReminderRepository reminderRepository;

    @Autowired
    public ReminderService(@Qualifier("MSSql") IReminderRepository reminderRepository){
        this.reminderRepository = reminderRepository;
    }

    public Reminder insertReminder (Reminder reminder){
        //TODO implement
        return reminderRepository.insertReminder(reminder);
    }

    public List<Reminder> selectAllRemindersForGivenUser (UUID patientId){
        //TODO implement
        return  reminderRepository.selectAllRemindersForGivenPatient(patientId);
    }

    public int deleteOldReminders (UUID patientId){
        //TODO implement
        return reminderRepository.deleteOldReminders(patientId);
    }

}
