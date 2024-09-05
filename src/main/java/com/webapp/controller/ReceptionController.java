// ReceptionController.java
package com.webapp.controller;

import com.webapp.dto.ReceptionDTO;
import com.webapp.service.ReceptionService;
import com.webapp.exception.InvalidRequestException;
import com.webapp.exception.RequestNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receptions")
public class ReceptionController {

    @Autowired
    private ReceptionService receptionService;

    @GetMapping
    public ResponseEntity<List<ReceptionDTO>> getAllReceptions() {
        List<ReceptionDTO> receptions = receptionService.getAllReceptions();
        return ResponseEntity.ok(receptions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceptionDTO> getReceptionById(@PathVariable int id) {
        ReceptionDTO reception = receptionService.getReceptionById(id);
        return ResponseEntity.ok(reception);
    }

    @PostMapping
    public ResponseEntity<ReceptionDTO> createReception(@RequestBody ReceptionDTO receptionDTO) {
        try {
            ReceptionDTO createdReception = receptionService.createReception(receptionDTO);
            return ResponseEntity.ok(createdReception);
        } catch (InvalidRequestException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReceptionDTO> updateReception(@PathVariable int id, @RequestBody ReceptionDTO receptionDTO) {
        try {
            ReceptionDTO updatedReception = receptionService.updateReception(id, receptionDTO);
            return ResponseEntity.ok(updatedReception);
        } catch (InvalidRequestException | RequestNotFoundException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReception(@PathVariable int id) {
        try {
            receptionService.deleteReception(id);
            return ResponseEntity.noContent().build();
        } catch (RequestNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
