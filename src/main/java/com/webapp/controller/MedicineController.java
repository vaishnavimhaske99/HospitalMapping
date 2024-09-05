// MedicineController.java
package com.webapp.controller;

import com.webapp.dto.MedicineDTO;
import com.webapp.exception.InvalidRequestException;
import com.webapp.exception.RequestNotFoundException;
import com.webapp.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicines")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping
    public ResponseEntity<List<MedicineDTO>> getAllMedicines() {
        List<MedicineDTO> medicines = medicineService.getAllMedicines();
        return ResponseEntity.ok(medicines);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicineDTO> getMedicineById(@PathVariable int id) {
        MedicineDTO medicine = medicineService.getMedicineById(id);
        return ResponseEntity.ok(medicine);
    }

    @PostMapping
    public ResponseEntity<MedicineDTO> createMedicine(@RequestBody MedicineDTO medicineDTO) {
        if (medicineDTO == null) {
            throw new InvalidRequestException("Invalid Medicine details provided");
        }
        MedicineDTO createdMedicine = medicineService.createMedicine(medicineDTO);
        return new ResponseEntity<>(createdMedicine, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicineDTO> updateMedicine(@PathVariable int id, @RequestBody MedicineDTO medicineDTO) {
        if (medicineDTO == null) {
            throw new InvalidRequestException("Invalid Medicine details provided");
        }
        MedicineDTO updatedMedicine = medicineService.updateMedicine(id, medicineDTO);
        return ResponseEntity.ok(updatedMedicine);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicine(@PathVariable int id) {
        try {
            medicineService.deleteMedicine(id);
        } catch (Exception e) {
            throw new RequestNotFoundException("Medicine not found with id " + id);
        }
        return ResponseEntity.noContent().build();
    }
}
