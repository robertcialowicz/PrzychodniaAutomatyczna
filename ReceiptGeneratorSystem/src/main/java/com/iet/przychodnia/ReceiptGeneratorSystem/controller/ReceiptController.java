package com.iet.przychodnia.ReceiptGeneratorSystem.controller;

import com.iet.przychodnia.ReceiptGeneratorSystem.model.Receipt;
import com.iet.przychodnia.ReceiptGeneratorSystem.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //@GetMapping(path = "/{id}")
    //public Receipt selectReceipt (@PathVariable UUID id){
    //    return receiptController.selectReceipt(id);
    //}

    //@GetMapping(path = "/pdf/{id}")
    //public Receipt generateReceipt (@PathVariable("id")UUID id){
    //    //TODO generate pdf or sth here
    //    return null;
    //}

}
