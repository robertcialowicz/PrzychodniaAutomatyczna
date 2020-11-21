package com.iet.przychodnia.ReceiptGeneratorSystem.controller;

import com.iet.przychodnia.ReceiptGeneratorSystem.model.Medical;
import com.iet.przychodnia.ReceiptGeneratorSystem.model.Receipt;
import com.iet.przychodnia.ReceiptGeneratorSystem.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/receipt")
public class ReceiptController {

    private final ReceiptService receiptService;

    @Autowired
    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping
    public Receipt insertReceipt (@RequestBody Receipt receipt){
        return receiptService.insertReceipt(receipt);
    }

    @GetMapping(path = "/patient/{id}")
    public List<Receipt> generateReceiptsForGivenPatient (@PathVariable("id") UUID patientId){
        return receiptService.getReceiptsForPatient(patientId);
    }

}