// ChargesServiceImpl.java
package com.webapp.serviceImpl;

import com.webapp.dto.ChargesDTO;
import com.webapp.model.Charges;
import com.webapp.repository.ChargeRepository;
import com.webapp.service.ChargesService;
import com.webapp.exception.InvalidRequestException;
import com.webapp.exception.RequestNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChargesServiceImpl implements ChargesService {

    @Autowired
    private ChargeRepository chargesRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ChargesDTO> getAllCharges() {
        return chargesRepository.findAll().stream()
                .map(charge -> modelMapper.map(charge, ChargesDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ChargesDTO getChargeById(int id) {
        Charges charge = chargesRepository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException("Charge not found with id " + id));
        return modelMapper.map(charge, ChargesDTO.class);
    }

    @Override
    public ChargesDTO createCharge(ChargesDTO chargesDTO) {
        if (chargesDTO == null) {
            throw new InvalidRequestException("Invalid Charge details provided");
        }
        Charges charge = modelMapper.map(chargesDTO, Charges.class);
        Charges createdCharge = chargesRepository.save(charge);
        return modelMapper.map(createdCharge, ChargesDTO.class);
    }

    @Override
    public ChargesDTO updateCharge(int id, ChargesDTO chargesDTO) {
        if (chargesDTO == null) {
            throw new InvalidRequestException("Invalid Charge details provided");
        }
        Charges existingCharge = chargesRepository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException("Charge not found with id " + id));

        existingCharge.setAmount(chargesDTO.getAmount());
        existingCharge.setDescription(chargesDTO.getDescription());
        // Set Reception, Pharmacist, and Bill if needed

        Charges updatedCharge = chargesRepository.save(existingCharge);
        return modelMapper.map(updatedCharge, ChargesDTO.class);
    }

    @Override
    public void deleteCharge(int id) {
        if (!chargesRepository.existsById(id)) {
            throw new RequestNotFoundException("Charge not found with id " + id);
        }
        chargesRepository.deleteById(id);
    }
}
