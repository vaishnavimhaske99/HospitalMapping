package com.webapp.controller;

import com.webapp.dto.BillDTO;
import com.webapp.service.BillService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bills")
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping
    public ResponseEntity<List<BillDTO>> getAllBills() {
        List<BillDTO> bills = billService.getAllBills();
        return ResponseEntity.ok(bills);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillDTO> getBillById(@PathVariable int id) {
        BillDTO bill = billService.getBillById(id);
        return ResponseEntity.ok(bill);
    }

    @PostMapping
    public ResponseEntity<BillDTO> createBill(@RequestBody BillDTO billDTO) {
        try {
            BillDTO createdBill = billService.createBill(billDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBill);
        } catch (Exception ex) {
            throw new RuntimeException("Error creating bill", ex);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BillDTO> updateBill(@PathVariable int id, @RequestBody BillDTO billDTO) {
        try {
            BillDTO updatedBill = billService.updateBill(id, billDTO);
            return ResponseEntity.ok(updatedBill);
        } catch (Exception ex) {
            throw new RuntimeException("Error updating bill", ex);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBill(@PathVariable int id) {
        try {
            billService.deleteBill(id);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            throw new RuntimeException("Error deleting bill", ex);
        }
    }
}
