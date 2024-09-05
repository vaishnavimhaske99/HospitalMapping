// DoctorService.java
package com.webapp.service;

import com.webapp.dto.DoctorDTO;
import java.util.List;

public interface DoctorService {
    List<DoctorDTO> getAllDoctors();
    DoctorDTO getDoctorById(int id);
    DoctorDTO createDoctor(DoctorDTO doctorDTO);
    DoctorDTO updateDoctor(int id, DoctorDTO doctorDTO);
    void deleteDoctor(int id);
}
