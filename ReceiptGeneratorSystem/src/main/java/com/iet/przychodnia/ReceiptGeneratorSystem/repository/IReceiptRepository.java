package com.iet.przychodnia.ReceiptGeneratorSystem.repository;

import com.iet.przychodnia.ReceiptGeneratorSystem.model.Medical;
import com.iet.przychodnia.ReceiptGeneratorSystem.model.Receipt;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IReceiptRepository {

    Receipt insertReceipt(UUID id, Receipt receipt);

    default Receipt insertReceipt(Receipt receipt){
        UUID id = UUID.randomUUID();
        return insertReceipt(id, receipt);
    }

    List<Receipt> getReceiptsForPatient(UUID id);

    List<Receipt> getReceiptsForVisit(UUID id);

    List<Receipt> getReceiptsForPatientForVisit(UUID patientID, UUID visitID);
}
