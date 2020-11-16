package com.iet.przychodnia.ReceiptGeneratorSystem.controller;

import com.iet.przychodnia.ReceiptGeneratorSystem.model.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/receipt")
public class ReceiptController {
    private final ReceiptController receiptController;

    @Autowired
    public ReceiptController(ReceiptController receiptController) {
        this.receiptController = receiptController;
    }

    @PostMapping
    public Receipt insertReceipt (@RequestBody Receipt receipt){
        return receiptController.insertReceipt(receipt);
    }

    @GetMapping(path = "/{id}")
    public Receipt selectReceipt (@RequestBody Receipt receipt){
        return receiptController.selectReceipt(receipt);
    }

    @GetMapping(path = "/pdf/{id}")
    public Receipt generateReceipt (@PathVariable("id")UUID id){
        //TODO generate pdf or sth here
        return null;
    }

}
