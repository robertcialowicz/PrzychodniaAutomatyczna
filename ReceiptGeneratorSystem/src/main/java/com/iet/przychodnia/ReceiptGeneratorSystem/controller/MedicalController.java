package com.iet.przychodnia.ReceiptGeneratorSystem.controller;

import com.iet.przychodnia.ReceiptGeneratorSystem.model.Medical;
import com.iet.przychodnia.ReceiptGeneratorSystem.model.Receipt;
import com.iet.przychodnia.ReceiptGeneratorSystem.service.MedicalService;
import com.iet.przychodnia.ReceiptGeneratorSystem.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController("medicals")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/medicals")
public class MedicalController {

    private final MedicalService medicalService;

    @Autowired
    public MedicalController(MedicalService medicalService) {
        this.medicalService = medicalService;
    }

    @PostMapping
    public Medical insertMedical (@RequestBody Medical medical){
        return medicalService.insertMedical(medical);
    }

    @GetMapping
    public List<Medical> getAllMedicals() {
        return medicalService.getAllMedicals();
    }

    @GetMapping(path = "/{id}")
    public Medical getMedicalById(@PathVariable("id") UUID uuid){
        return medicalService.getMedicalById(uuid)
                .orElse(null);
    }

}