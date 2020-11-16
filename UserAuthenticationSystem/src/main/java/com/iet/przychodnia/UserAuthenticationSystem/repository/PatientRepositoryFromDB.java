package com.iet.przychodnia.UserAuthenticationSystem.repository;

import com.iet.przychodnia.UserAuthenticationSystem.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("MSSqlPatient")
public class PatientRepositoryFromDB implements IPatientRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PatientRepositoryFromDB(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Patient insertPatient(UUID id, Patient patient) {
        try {
            final String sql = "INSERT INTO Patients (id, name, surname, pesel, email, birthday) VALUES (?, ?, ?, ?, ?, ?)";
            Patient patientToUpload = new Patient(id, patient.getName(), patient.getSurname(), patient.getPesel(), patient.getEmail(), patient.getBirthday());
            jdbcTemplate.update(sql, patientToUpload.getId(), patientToUpload.getName(), patientToUpload.getSurname(), patientToUpload.getPesel(), patientToUpload.getEmail(), patientToUpload.getBirthday());
            return patientToUpload;
        }catch(Exception e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public List<Patient> selectAllPatients() {
        final String sql = "SELECT id, name, surname, pesel, email, birthday FROM Patients";
        List<Patient> patients = jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id").trim());
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String pesel = resultSet.getString("pesel");
            String email = resultSet.getString("email");
            String birthday = resultSet.getString("birthday");
            return new Patient(id, name, surname, pesel, email, birthday);
        });
        return patients;
    }

    @Override
    public Optional<Patient> selectPatientById(UUID id) {
        final String sql = "SELECT id, name, surname, pesel, email, birthday FROM Patients WHERE id=?";
        Patient patient = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (resultSet, i) -> {
                    UUID patientID = UUID.fromString(resultSet.getString("id").trim());
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    String pesel = resultSet.getString("pesel");
                    String email = resultSet.getString("email");
                    String birthday = resultSet.getString("birthday");
                    return new Patient(patientID, name, surname, pesel, email, birthday);
                });
        return Optional.ofNullable(patient);
    }

    @Override
    public int deletePatientById(UUID id) {
        try {
            final String sql = "DELETE FROM Patients WHERE id=?";
            jdbcTemplate.update(sql,id);
            return 0;
        }catch(Exception e) {
            e.getMessage();
            return 1;
        }
    }

    @Override
    public int updatePatientById(UUID id, Patient patient) {
        return 0;
        //TODO implement updatePatientById
    }
}
