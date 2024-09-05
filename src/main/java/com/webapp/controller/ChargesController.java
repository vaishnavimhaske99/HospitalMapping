// ChargesController.java
package com.webapp.controller;

import com.webapp.dto.ChargesDTO;
import com.webapp.exception.InvalidRequestException;
import com.webapp.exception.RequestNotFoundException;
import com.webapp.service.ChargesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/charges")
public class ChargesController {

    @Autowired
    private ChargesService chargesService;

    @GetMapping
    public ResponseEntity<List<ChargesDTO>> getAllCharges() {
        List<ChargesDTO> charges = chargesService.getAllCharges();
        return ResponseEntity.ok(charges);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChargesDTO> getChargeById(@PathVariable int id) {
        ChargesDTO charge = chargesService.getChargeById(id);
        return ResponseEntity.ok(charge);
    }

    @PostMapping
    public ResponseEntity<ChargesDTO> createCharge(@RequestBody ChargesDTO chargesDTO) {
        if (chargesDTO == null) {
            throw new InvalidRequestException("Invalid Charge details provided");
        }
        ChargesDTO createdCharge = chargesService.createCharge(chargesDTO);
        return new ResponseEntity<>(createdCharge, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChargesDTO> updateCharge(@PathVariable int id, @RequestBody ChargesDTO chargesDTO) {
        if (chargesDTO == null) {
            throw new InvalidRequestException("Invalid Charge details provided");
        }
        ChargesDTO updatedCharge = chargesService.updateCharge(id, chargesDTO);
        return ResponseEntity.ok(updatedCharge);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharge(@PathVariable int id) {
        try {
            chargesService.deleteCharge(id);
        } catch (Exception e) {
            throw new RequestNotFoundException("Charge not found with id " + id);
        }
        return ResponseEntity.noContent().build();
    }
}
