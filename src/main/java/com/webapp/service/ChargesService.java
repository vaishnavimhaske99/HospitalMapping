// ChargesService.java
package com.webapp.service;

import com.webapp.dto.ChargesDTO;
import java.util.List;

public interface ChargesService {
    List<ChargesDTO> getAllCharges();
    ChargesDTO getChargeById(int id);
    ChargesDTO createCharge(ChargesDTO chargesDTO);
    ChargesDTO updateCharge(int id, ChargesDTO chargesDTO);
    void deleteCharge(int id);
}
