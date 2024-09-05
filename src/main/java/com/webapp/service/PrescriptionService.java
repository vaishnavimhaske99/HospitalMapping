// PrescriptionService.java
package com.webapp.service;

import com.webapp.dto.PrescriptionDTO;
import java.util.List;

public interface PrescriptionService {
    List<PrescriptionDTO> getAllPrescriptions();
    PrescriptionDTO getPrescriptionById(int id);
    PrescriptionDTO createPrescription(PrescriptionDTO prescriptionDTO);
    PrescriptionDTO updatePrescription(int id, PrescriptionDTO prescriptionDTO);
    void deletePrescription(int id);
}
