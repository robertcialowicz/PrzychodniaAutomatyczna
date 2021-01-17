package com.iet.przychodnia.VisitReminderSystem.repository;

import com.iet.przychodnia.VisitReminderSystem.model.Reminder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
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
        try {
            final String sql = "INSERT INTO Reminders (id, patientID, doctorID, visitID, datetime) VALUES (?, ?, ?, ?, ?)";
            Reminder reminderToUpload = new Reminder(id, reminder.getPatientID(), reminder.getDoctorID(), reminder.getVisitID(), reminder.getDatetime());
            jdbcTemplate.update(sql, reminderToUpload.getId(), reminderToUpload.getPatientID(), reminderToUpload.getDoctorID(), reminder.getVisitID(), reminderToUpload.getDatetime());
            return reminderToUpload;
        }catch(Exception e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public List<Reminder> selectAllRemindersForGivenPatient(UUID patientId) {
        final String sql = "SELECT id, patientID, doctorID, visitID, datetime FROM Reminders WHERE patientID=?";
        try{
        List<Reminder> reminders = jdbcTemplate.query(
                sql,
                new Object[]{patientId.toString()},
                (resultSet, i) -> {
                    UUID id = UUID.fromString(resultSet.getString("id").trim());
                    String datetime = resultSet.getString("datetime").trim();
                    UUID visitID = UUID.fromString(resultSet.getString("visitID").trim());
                    UUID doctorID = UUID.fromString(resultSet.getString("doctorID").trim());
                    UUID patientID = UUID.fromString(resultSet.getString("patientID").trim());
                    return new Reminder(id, patientID, doctorID, visitID, datetime);
                });

            return reminders;

        }catch(Exception e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public int deleteOldReminders(UUID patientId) {
        try {
            final String sql = "DELETE FROM Reminders WHERE (patientID=?) AND (datetime < ?)";
            //TODO implement as column 'date' is not accessible
            //jdbcTemplate.update(sql, Instant.now().minus(1, ChronoUnit.WEEKS));
            return 0;
        }catch(Exception e) {
            e.getMessage();
            return 1;
        }
    }

    @Override
    public int deleteReminderById(UUID id) {
        try{
            final String sql = "DELETE FROM Reminders WHERE id=?";
            jdbcTemplate.update(sql,id);
            return 0;
        }catch (Exception e){
            e.getMessage();
            return 1;
        }
    }

    @Override
    public int deleteReminderByVisitId(UUID id) {
        try{
            final String sql = "DELETE FROM Reminders WHERE visitID=?";
            jdbcTemplate.update(sql,id);
            return 0;
        }catch (Exception e){
            e.getMessage();
            return 1;
        }
    }
}
