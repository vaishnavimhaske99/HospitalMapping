// PatientService.java
package com.webapp.service;

import com.webapp.dto.PatientDTO;
import java.util.List;

public interface PatientService {
    List<PatientDTO> getAllPatients();
    PatientDTO getPatientById(int id);
    PatientDTO createPatient(PatientDTO patientDTO);
    PatientDTO updatePatient(int id, PatientDTO patientDTO);
    void deletePatient(int id);
}
