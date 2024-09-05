// PharmacistService.java
package com.webapp.service;

import com.webapp.dto.PharmacistDTO;
import java.util.List;

public interface PharmacistService {
    List<PharmacistDTO> getAllPharmacists();
    PharmacistDTO getPharmacistById(int id);
    PharmacistDTO createPharmacist(PharmacistDTO pharmacistDTO);
    PharmacistDTO updatePharmacist(int id, PharmacistDTO pharmacistDTO);
    void deletePharmacist(int id);
}
