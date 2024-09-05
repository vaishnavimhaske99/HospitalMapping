// PrescriptionController.java
package com.webapp.controller;

import com.webapp.dto.PrescriptionDTO;
import com.webapp.exception.InvalidRequestException;
import com.webapp.exception.RequestNotFoundException;
import com.webapp.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping
    public ResponseEntity<List<PrescriptionDTO>> getAllPrescriptions() {
        List<PrescriptionDTO> prescriptions = prescriptionService.getAllPrescriptions();
        return ResponseEntity.ok(prescriptions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionDTO> getPrescriptionById(@PathVariable int id) {
        PrescriptionDTO prescription = prescriptionService.getPrescriptionById(id);
        return ResponseEntity.ok(prescription);
    }

    @PostMapping
    public ResponseEntity<PrescriptionDTO> createPrescription(@RequestBody PrescriptionDTO prescriptionDTO) {
        if (prescriptionDTO == null) {
            throw new InvalidRequestException("Invalid Prescription details provided");
        }
        PrescriptionDTO createdPrescription = prescriptionService.createPrescription(prescriptionDTO);
        return new ResponseEntity<>(createdPrescription, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrescriptionDTO> updatePrescription(@PathVariable int id, @RequestBody PrescriptionDTO prescriptionDTO) {
        if (prescriptionDTO == null) {
            throw new InvalidRequestException("Invalid Prescription details provided");
        }
        PrescriptionDTO updatedPrescription = prescriptionService.updatePrescription(id, prescriptionDTO);
        return ResponseEntity.ok(updatedPrescription);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrescription(@PathVariable int id) {
        try {
            prescriptionService.deletePrescription(id);
        } catch (Exception e) {
            throw new RequestNotFoundException("Prescription not found with id " + id);
        }
        return ResponseEntity.noContent().build();
    }
}
