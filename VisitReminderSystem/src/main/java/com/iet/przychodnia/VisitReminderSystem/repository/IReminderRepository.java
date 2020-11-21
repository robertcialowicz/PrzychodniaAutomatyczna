package com.iet.przychodnia.VisitReminderSystem.repository;

import com.iet.przychodnia.VisitReminderSystem.model.Reminder;

import java.util.List;
import java.util.UUID;

public interface IReminderRepository {
    Reminder insertReminder(UUID id, Reminder reminder);

    default Reminder insertReminder(Reminder reminder){
        UUID id = UUID.randomUUID();
        return insertReminder(id, reminder);
    }

    List<Reminder> selectAllRemindersForGivenPatient(UUID patientId);

    int deleteOldReminders(UUID patientId);

}
