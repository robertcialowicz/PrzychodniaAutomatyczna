package com.iet.przychodnia.ReceiptGeneratorSystem.repository;

import com.iet.przychodnia.ReceiptGeneratorSystem.model.Medical;
import com.iet.przychodnia.ReceiptGeneratorSystem.model.Receipt;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IMedicalRepository {
    Medical insertMedical(UUID id, Medical medical);

    default Medical insertMedical(Medical medical){
        UUID id = UUID.randomUUID();
        return insertMedical(id, medical);
    }

    List<Medical> getMedicals();

    Optional<Medical> getMedicalById(UUID id);
}
