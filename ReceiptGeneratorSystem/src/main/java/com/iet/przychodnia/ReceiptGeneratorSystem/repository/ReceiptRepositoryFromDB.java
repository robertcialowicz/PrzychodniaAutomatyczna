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
            final String sql = "INSERT INTO Receipts (id, patientID, visitID, medicalsID) VALUES (?, ?, ?, ?)";
            Receipt receiptToUpload = new Receipt(id, receipt.getPatientId(), receipt.getVisitId(), receipt.getMedicalIds());
            jdbcTemplate.update(sql, receiptToUpload.getId(), receiptToUpload.getPatientId(), receiptToUpload.getVisitId(), receiptToUpload.getMedicalIds());
            return receiptToUpload;
        }catch(Exception e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public List<Receipt> getReceiptsForPatient(UUID patientId) {
        final String sql = "SELECT id, patientID, visitId, medicalsID FROM Receipts WHERE patientID=?";
        List<Receipt> receipts = jdbcTemplate.query(
                sql,
                new Object[]{patientId.toString()},
                (resultSet, i) -> {
                    UUID id = UUID.fromString(resultSet.getString("id").trim());
                    UUID visitID = UUID.fromString(resultSet.getString("visitID").trim());
                    UUID patientID = UUID.fromString(resultSet.getString("patientID").trim());
                    UUID medicalsID = (resultSet.getString("medicalsID") != null) ?
                            UUID.fromString(resultSet.getString("medicalsID").trim()) : null;
                    return new Receipt(id, patientID, visitID, medicalsID);
                });
        return receipts;
    }

    @Override
    public List<Receipt> getReceiptsForVisit(UUID visitid) {
        final String sql = "SELECT id, patientID, visitId, medicalsID FROM Receipts WHERE visitID=?";
        List<Receipt> receipts = jdbcTemplate.query(
                sql,
                new Object[]{visitid.toString()},
                (resultSet, i) -> {
                    UUID id = UUID.fromString(resultSet.getString("id").trim());
                    UUID visitID = UUID.fromString(resultSet.getString("visitID").trim());
                    UUID patientID = UUID.fromString(resultSet.getString("patientID").trim());
                    UUID medicalsID = (resultSet.getString("medicalsID") != null) ?
                            UUID.fromString(resultSet.getString("medicalsID").trim()) : null;
                    return new Receipt(id, patientID, visitID, medicalsID);
                });
        return receipts;
    }

    @Override
    public List<Receipt> getReceiptsForPatientForVisit(UUID patientID, UUID visitID) {
        final String sql = "SELECT id, patientID, visitId, medicalsID FROM Receipts WHERE (patientID=? AND visitId=?)";
        List<Receipt> receipts = jdbcTemplate.query(
                sql,
                new Object[]{patientID.toString(), visitID.toString()},
                (resultSet, i) -> {
                    UUID id = UUID.fromString(resultSet.getString("id").trim());
                    UUID visitid = UUID.fromString(resultSet.getString("visitID").trim());
                    UUID patientid = UUID.fromString(resultSet.getString("patientID").trim());
                    UUID medicalsID = (resultSet.getString("medicalsID") != null) ?
                            UUID.fromString(resultSet.getString("medicalsID").trim()) : null;
                    return new Receipt(id, visitid, patientid, medicalsID);
                });
        return receipts;
    }

    @Override
    public int deleteReceiptsByVisitId(UUID id) {
        try {
            final String sql = "DELETE FROM Receipts WHERE visitId=?";
            jdbcTemplate.update(sql,id);
            return 0;
        }catch(Exception e) {
            e.getMessage();
            return 1;
        }
    }
}
