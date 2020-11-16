package com.iet.przychodnia.ReceiptGeneratorSystem.repository;

import com.iet.przychodnia.ReceiptGeneratorSystem.model.Receipt;

import java.util.UUID;

public interface IReceiptRepository {

    Receipt insertReceipt(UUID id, Receipt receipt);

    default Receipt insertReceipt(Receipt receipt){
        UUID id = UUID.randomUUID();
        return insertReceipt(id, receipt);
    }

    Receipt selectReceipt(UUID id);
}
