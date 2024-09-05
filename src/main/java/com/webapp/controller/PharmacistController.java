// PharmacistController.java
package com.webapp.controller;

import com.webapp.dto.PharmacistDTO;
import com.webapp.exception.InvalidRequestException;
import com.webapp.exception.RequestNotFoundException;
import com.webapp.service.PharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pharmacists")
public class PharmacistController {

    @Autowired
    private PharmacistService pharmacistService;

    @GetMapping
    public ResponseEntity<List<PharmacistDTO>> getAllPharmacists() {
        List<PharmacistDTO> pharmacists = pharmacistService.getAllPharmacists();
        return ResponseEntity.ok(pharmacists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PharmacistDTO> getPharmacistById(@PathVariable int id) {
        PharmacistDTO pharmacist = pharmacistService.getPharmacistById(id);
        return ResponseEntity.ok(pharmacist);
    }

    @PostMapping
    public ResponseEntity<PharmacistDTO> createPharmacist(@RequestBody PharmacistDTO pharmacistDTO) {
        if (pharmacistDTO == null) {
            throw new InvalidRequestException("Invalid Pharmacist details provided");
        }
        PharmacistDTO createdPharmacist = pharmacistService.createPharmacist(pharmacistDTO);
        return new ResponseEntity<>(createdPharmacist, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PharmacistDTO> updatePharmacist(@PathVariable int id, @RequestBody PharmacistDTO pharmacistDTO) {
        if (pharmacistDTO == null) {
            throw new InvalidRequestException("Invalid Pharmacist details provided");
        }
        PharmacistDTO updatedPharmacist = pharmacistService.updatePharmacist(id, pharmacistDTO);
        return ResponseEntity.ok(updatedPharmacist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePharmacist(@PathVariable int id) {
        try {
            pharmacistService.deletePharmacist(id);
        } catch (Exception e) {
            throw new RequestNotFoundException("Pharmacist not found with id " + id);
        }
        return ResponseEntity.noContent().build();
    }
}
