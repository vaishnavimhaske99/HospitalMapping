// MedicineService.java
package com.webapp.service;

import com.webapp.dto.MedicineDTO;
import java.util.List;

public interface MedicineService {
    List<MedicineDTO> getAllMedicines();
    MedicineDTO getMedicineById(int id);
    MedicineDTO createMedicine(MedicineDTO medicineDTO);
    MedicineDTO updateMedicine(int id, MedicineDTO medicineDTO);
    void deleteMedicine(int id);
}
