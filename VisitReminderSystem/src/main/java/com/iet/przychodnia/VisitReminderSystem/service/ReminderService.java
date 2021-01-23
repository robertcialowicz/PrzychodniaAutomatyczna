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
        return reminderRepository.insertReminder(reminder);
    }

    public List<Reminder> selectAllRemindersForGivenUser (UUID patientId){
        deleteOldReminders(patientId);
        return reminderRepository.selectAllRemindersForGivenPatient(patientId);
    }

    public int deleteOldReminders (UUID patientId){
        return reminderRepository.deleteOldReminders(patientId);
    }

    public int deleteReminderById(UUID id){
        return reminderRepository.deleteReminderById(id);
    }

    public int deleteReminderByVisitId(UUID id){
        return reminderRepository.deleteReminderByVisitId(id);
    }
}
