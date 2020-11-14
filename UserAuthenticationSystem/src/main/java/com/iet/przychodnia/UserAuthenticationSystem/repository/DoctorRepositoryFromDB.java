package com.iet.przychodnia.UserAuthenticationSystem.repository;

import com.iet.przychodnia.UserAuthenticationSystem.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("MSSqlDoctor")
public class DoctorRepositoryFromDB implements IDoctorRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DoctorRepositoryFromDB(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Doctor insertDoctor(UUID id, Doctor doctor) {
        try {
            final String sql = "INSERT INTO Doctors (id, name, surname, email) VALUES (?, ?, ?, ?)";
            Doctor doctorToUpload = new Doctor(id, doctor.getName(), doctor.getSurname(), doctor.getEmail());
            jdbcTemplate.update(sql, doctorToUpload.getId(), doctorToUpload.getName(), doctorToUpload.getSurname(), doctorToUpload.getEmail());
            return doctorToUpload;
        }catch(Exception e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public List<Doctor> selectAllDoctors() {
        final String sql = "SELECT id, name, surname, email FROM Doctors";
        List<Doctor> doctors = jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id").trim());
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String email = resultSet.getString("email");
            return new Doctor(id, name, surname, email);
        });
        return doctors;
    }

    @Override
    public Optional<Doctor> selectDoctorById(UUID id) {
        final String sql = "SELECT id, name, surname, email FROM Doctors WHERE id=?";
        Doctor doctor = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (resultSet, i) -> {
                    UUID doctorID = UUID.fromString(resultSet.getString("id").trim());
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    String email = resultSet.getString("email");
                    return new Doctor(doctorID, name, surname, email);
                });
        return Optional.ofNullable(doctor);
    }

    @Override
    public int deleteDoctorById(UUID id) {
        try {
            final String sql = "DELETE FROM Doctors WHERE id=?";
            jdbcTemplate.update(sql,id);
            return 0;
        }catch(Exception e) {
            e.getMessage();
            return 1;
        }
    }

    @Override
    public int updateDoctorById(UUID id, Doctor doctor) {
        return 0;
        //TODO implement updateDoctorById
    }
}
