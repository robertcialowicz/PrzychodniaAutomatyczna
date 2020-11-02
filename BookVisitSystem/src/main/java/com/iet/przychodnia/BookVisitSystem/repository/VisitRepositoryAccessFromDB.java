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
    public int insertVisit(UUID id, Visit visit) {
        return 0;
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
        final String sql = "SELECT id, date, notes FROM Visits";
        List<Visit> visits = jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID tempId = UUID.fromString(resultSet.getString("id").trim());
            String date = resultSet.getString("date");
            String notes = resultSet.getString("notes");
            return new Visit(tempId, date, notes);
        });
        return visits.stream()
                .filter(v-> v.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteVisitById(UUID id) {
        return 0;
    }

    @Override
    public int updateVisitById(UUID id, Visit visit) {
        return 0;
    }
}
