// ReceptionService.java
package com.webapp.service;

import com.webapp.dto.ReceptionDTO;

import java.util.List;

public interface ReceptionService {

    List<ReceptionDTO> getAllReceptions();

    ReceptionDTO getReceptionById(int id);

    ReceptionDTO createReception(ReceptionDTO receptionDTO);

    ReceptionDTO updateReception(int id, ReceptionDTO receptionDTO);

    void deleteReception(int id);
}
