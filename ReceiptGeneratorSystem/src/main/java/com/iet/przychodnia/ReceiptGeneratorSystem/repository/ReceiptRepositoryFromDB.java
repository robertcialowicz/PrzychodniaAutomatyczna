package com.iet.przychodnia.ReceiptGeneratorSystem.repository;

import com.iet.przychodnia.ReceiptGeneratorSystem.model.Receipt;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("MSSqlReceipt")
public class ReceiptRepositoryFromDB implements IReceiptRepository {

    @Override
    public Receipt insertReceipt(UUID id, Receipt receipt) {
        return null;
    }

    @Override
    public Receipt selectReceipt(UUID id) {
        return null;
    }
}
