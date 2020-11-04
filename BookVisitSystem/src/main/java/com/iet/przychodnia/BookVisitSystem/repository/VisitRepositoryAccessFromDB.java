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
    public UUID insertVisit(UUID id, Visit visit) {
        try {
            final String sql = "INSERT INTO Visits (id, date, notes) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, id, visit.getDate(), visit.getNotes());
            return id;
        }catch(Exception e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public List<Visit> selectAllVisits() {
        final String sql = "SELECT id, date, notes FROM Visits";
        List<Visit> visits = jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id").trim());
            String date = resultSet.getString("date");
            String notes = resultSet.getString("notes");
            return new Visit(id, date, notes);
        });
        return visits;
    }

    @Override
    public Optional<Visit> selectVisitById(UUID id) {
        final String sql = "SELECT id, date, notes FROM Visits WHERE id = ?";
        Visit visit = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (resultSet, i) -> {
                    UUID visitId = UUID.fromString(resultSet.getString("id").trim());
                    String date = resultSet.getString("date");
                    String notes = resultSet.getString("notes");
                    return new Visit(visitId, date, notes);
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
    public int updateVisitById(UUID id, Visit visit) {
        return 0;
    }
}
