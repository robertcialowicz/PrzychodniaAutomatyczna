package com.iet.przychodnia.ReceiptGeneratorSystem.service;

import com.iet.przychodnia.ReceiptGeneratorSystem.model.Medical;
import com.iet.przychodnia.ReceiptGeneratorSystem.model.Receipt;
import com.iet.przychodnia.ReceiptGeneratorSystem.repository.IReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReceiptService {

    private final IReceiptRepository receiptRepository;

    @Autowired
    public ReceiptService(@Qualifier("MSSqlReceipt") IReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    public Receipt insertReceipt(Receipt receipt){
        return receiptRepository.insertReceipt(receipt);
    }

    public List<Medical> getReceiptsForPatient(UUID patientId){
        //TODO implement
        return null;
    }

}
