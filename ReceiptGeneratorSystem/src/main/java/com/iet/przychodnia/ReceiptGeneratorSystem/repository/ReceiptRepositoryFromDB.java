package com.iet.przychodnia.ReceiptGeneratorSystem.repository;

import com.iet.przychodnia.ReceiptGeneratorSystem.model.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("MSSqlReceipt")
public class ReceiptRepositoryFromDB implements IReceiptRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReceiptRepositoryFromDB(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Receipt insertReceipt(UUID id, Receipt receipt) {
        try {
            final String sql = "INSERT INTO Receipts (id, patientID, doctorID, medicalsID) VALUES (?, ?, ?, ?)";
            Receipt receiptToUpload = new Receipt(id, receipt.getPatientId(), receipt.getDoctorId(), receipt.getMedicalIds());
            jdbcTemplate.update(sql, receiptToUpload.getId(), receiptToUpload.getPatientId(), receiptToUpload.getDoctorId(), receiptToUpload.getMedicalIds());
            return receiptToUpload;
        }catch(Exception e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public List<Receipt> getReceiptsForPatient(UUID patientId) {
        final String sql = "SELECT id, patientID, doctorID, medicalsID FROM Receipts WHERE patientID=?";
        List<Receipt> receipts = jdbcTemplate.query(
                sql,
                new Object[]{patientId.toString()},
                (resultSet, i) -> {
                    UUID id = UUID.fromString(resultSet.getString("id").trim());
                    UUID doctorID = UUID.fromString(resultSet.getString("doctorID").trim());
                    UUID patientID = UUID.fromString(resultSet.getString("patientID").trim());
                    UUID medicalsID = (resultSet.getString("medicalsID") != null) ?
                            UUID.fromString(resultSet.getString("medicalsID").trim()) : null;
                    return new Receipt(id, patientID, doctorID, medicalsID);
                });
        return receipts;
    }
}
