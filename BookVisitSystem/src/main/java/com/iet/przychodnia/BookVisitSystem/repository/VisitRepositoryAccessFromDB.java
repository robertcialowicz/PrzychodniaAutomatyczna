package com.iet.przychodnia.BookVisitSystem.repository;

import com.iet.przychodnia.BookVisitSystem.model.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("MSSql")
public class VisitRepositoryAccessFromDB implements IVisitRepository {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public VisitRepositoryAccessFromDB(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Visit insertVisit(UUID id, Visit visit) {
        try {
            final String sql = "INSERT INTO Visits (id, date, doctorID, patientID, specializationID) VALUES (?, ?, ?, ?, ?)";
            Visit visitToUpload = new Visit(id, visit.getDatetime(), null, visit.getDoctorID(), visit.getPatientID(), visit.getSpecializationID(), null);
            jdbcTemplate.update(sql, visitToUpload.getId(), visitToUpload.getDatetime(), visitToUpload.getDoctorID(), visitToUpload.getPatientID(), visitToUpload.getSpecializationID());
            return visitToUpload;
        }catch(Exception e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public List<Visit> selectAllVisits() {
        final String sql = "SELECT id, date, notes, doctorID, patientID, specializationID, medicalsID FROM Visits";
        List<Visit> visits = jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id").trim());
            String date = resultSet.getString("date");
            String notes = resultSet.getString("notes");
            UUID doctorID = UUID.fromString(resultSet.getString("doctorID").trim());
            UUID patientID = UUID.fromString(resultSet.getString("patientID").trim());
            UUID specializationID = UUID.fromString(resultSet.getString("specializationID").trim());
            UUID medicalsID = (resultSet.getString("medicalsID") != null) ?
                    UUID.fromString(resultSet.getString("medicalsID").trim()) : null;
            return new Visit(id, date, notes, doctorID, patientID, specializationID, medicalsID);
        });
        return visits;
    }

    @Override
    public Optional<Visit> selectVisitById(UUID id) {
        final String sql = "SELECT id, date, notes, doctorID, patientID, specializationID, medicalsID FROM Visits WHERE id=?";
        Visit visit = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (resultSet, i) -> {
                    UUID visitID = UUID.fromString(resultSet.getString("id").trim());
                    String date = resultSet.getString("date");
                    String notes = resultSet.getString("notes");
                    UUID doctorID = UUID.fromString(resultSet.getString("doctorID").trim());
                    UUID patientID = UUID.fromString(resultSet.getString("patientID").trim());
                    UUID specializationID = UUID.fromString(resultSet.getString("specializationID").trim());
                    UUID medicalsID = (resultSet.getString("medicalsID") != null) ?
                            UUID.fromString(resultSet.getString("medicalsID").trim()) : null;
                    return new Visit(visitID, date, notes, doctorID, patientID, specializationID, medicalsID);
                });

        return Optional.ofNullable(visit);
    }

    @Override
    public int deleteVisitById(UUID id) {
        try {
            final String sql = "DELETE FROM Visits WHERE id=?";
            jdbcTemplate.update(sql,id);
            return 0;
        }catch(Exception e) {
            e.getMessage();
            return 1;
        }
    }

    @Override
    public Visit updateVisitById(UUID id, Visit visit) {
        try {
            final String sql = "UPDATE Visits SET notes=?, medicalsID=? WHERE id=?";
            Visit visitToUpload = new Visit(id, visit.getDatetime(), visit.getNotes(), visit.getDoctorID(), visit.getPatientID(), visit.getSpecializationID(), visit.getMediacalsID());
            jdbcTemplate.update(sql, visitToUpload.getNotes(), visitToUpload.getMediacalsID(), id);
            return selectVisitById(id).orElse(null);
        }catch(Exception e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public List<Visit> searchForVisitsInGivenPeriod(String fromDate, String toDate) {
        final String sql = "SELECT id, date, notes, doctorID, patientID, specializationID, medicalsID FROM Visits WHERE date BETWEEN ? AND ?";
        List<Visit> visits = jdbcTemplate.query(
                sql,
                new Object[]{fromDate, toDate},
                (resultSet, i) -> {
                    UUID id = UUID.fromString(resultSet.getString("id").trim());
                    String date = resultSet.getString("date");
                    String notes = resultSet.getString("notes");
                    UUID doctorID = UUID.fromString(resultSet.getString("doctorID").trim());
                    UUID patientID = UUID.fromString(resultSet.getString("patientID").trim());
                    UUID specializationID = UUID.fromString(resultSet.getString("specializationID").trim());
                    UUID medicalsID = (resultSet.getString("medicalsID") != null) ?
                            UUID.fromString(resultSet.getString("medicalsID").trim()) : null;
                    return new Visit(id, date, notes, doctorID, patientID, specializationID, medicalsID);
        });
        return visits;
    }
}
