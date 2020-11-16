package com.iet.przychodnia.ReceiptGeneratorSystem.service;

import com.iet.przychodnia.ReceiptGeneratorSystem.model.Receipt;
import com.iet.przychodnia.ReceiptGeneratorSystem.repository.IReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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

    public Receipt generateReceipt(UUID id){
        //TODO generate pdf or sth here
        return null;
    }

}
