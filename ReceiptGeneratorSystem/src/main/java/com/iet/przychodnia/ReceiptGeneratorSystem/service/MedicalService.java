package com.iet.przychodnia.ReceiptGeneratorSystem.service;

import com.iet.przychodnia.ReceiptGeneratorSystem.model.Medical;
import com.iet.przychodnia.ReceiptGeneratorSystem.model.Receipt;
import com.iet.przychodnia.ReceiptGeneratorSystem.repository.IMedicalRepository;
import com.iet.przychodnia.ReceiptGeneratorSystem.repository.IReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MedicalService {

    private final IMedicalRepository medicalRepository;

    @Autowired
    public MedicalService(@Qualifier("MSSqlMedical") IMedicalRepository medicalRepository) {
        this.medicalRepository = medicalRepository;
    }

    public Medical insertMedical(Medical medical){
        return medicalRepository.insertMedical(medical);
    }

    public List<Medical> getAllMedicals(){
        return medicalRepository.getMedicals();
    }

    public Optional<Medical> getMedicalById(UUID uuid){
        return medicalRepository.getMedicalById(uuid);
    }


}
