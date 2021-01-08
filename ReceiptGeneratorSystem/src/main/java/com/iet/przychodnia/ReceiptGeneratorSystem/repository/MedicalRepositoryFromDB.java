package com.iet.przychodnia.ReceiptGeneratorSystem.repository;

import com.iet.przychodnia.ReceiptGeneratorSystem.model.Medical;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("MSSqlMedical")
public class MedicalRepositoryFromDB implements IMedicalRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MedicalRepositoryFromDB(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Medical insertMedical(UUID id, Medical medical) {
        try {
            final String sql = "INSERT INTO Medicals (id, name) VALUES (?, ?)";
            Medical medicalToUpload = new Medical(id, medical.getName());
            jdbcTemplate.update(sql, medicalToUpload.getId(), medicalToUpload.getName());
            return medicalToUpload;
        }catch(Exception e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public List<Medical> getMedicals() {
        final String sql = "SELECT id, name FROM Medicals";
        List<Medical> medicals = jdbcTemplate.query(sql, (resultSet, i) -> {
                UUID id = UUID.fromString(resultSet.getString("id").trim());
                String name = resultSet.getString("name");
                return new Medical(id, name);
            });
        return medicals;
    }

    @Override
    public Optional<Medical> getMedicalById(UUID id){
        final String sql = "SELECT id, name FROM Medicals WHERE id=?";
        Medical medical = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (resultSet, i) -> {
                    UUID medicalID = UUID.fromString(resultSet.getString("id").trim());
                    String name = resultSet.getString("name");
                    return new Medical(medicalID, name);
                });
        return Optional.ofNullable(medical);
    }

}
