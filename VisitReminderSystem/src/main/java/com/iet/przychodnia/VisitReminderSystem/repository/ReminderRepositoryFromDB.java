package com.iet.przychodnia.VisitReminderSystem.repository;

import com.iet.przychodnia.VisitReminderSystem.model.Reminder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("MSSql")
public class ReminderRepositoryFromDB implements IReminderRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReminderRepositoryFromDB(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Reminder insertReminder(UUID id, Reminder reminder) {
        return null;
    }

    @Override
    public List<Reminder> selectAllRemindersForGivenPatient(UUID patientId) {
        return null;
    }

    @Override
    public int deleteOldReminders(UUID patientId) {
        return 0;
    }
}
