// PharmacistServiceImpl.java
package com.webapp.serviceImpl;

import com.webapp.dto.PharmacistDTO;
import com.webapp.model.Pharmacist;
import com.webapp.repository.PharmacistRepository;
import com.webapp.service.PharmacistService;
import com.webapp.exception.InvalidRequestException;
import com.webapp.exception.RequestNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PharmacistServiceImpl implements PharmacistService {

    @Autowired
    private PharmacistRepository pharmacistRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PharmacistDTO> getAllPharmacists() {
        return pharmacistRepository.findAll().stream()
                .map(pharmacist -> modelMapper.map(pharmacist, PharmacistDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PharmacistDTO getPharmacistById(int id) {
        Pharmacist pharmacist = pharmacistRepository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException("Pharmacist not found with id " + id));
        return modelMapper.map(pharmacist, PharmacistDTO.class);
    }

    @Override
    public PharmacistDTO createPharmacist(PharmacistDTO pharmacistDTO) {
        if (pharmacistDTO == null) {
            throw new InvalidRequestException("Invalid Pharmacist details provided");
        }
        Pharmacist pharmacist = modelMapper.map(pharmacistDTO, Pharmacist.class);
        Pharmacist createdPharmacist = pharmacistRepository.save(pharmacist);
        return modelMapper.map(createdPharmacist, PharmacistDTO.class);
    }

    @Override
    public PharmacistDTO updatePharmacist(int id, PharmacistDTO pharmacistDTO) {
        if (pharmacistDTO == null) {
            throw new InvalidRequestException("Invalid Pharmacist details provided");
        }
        Pharmacist existingPharmacist = pharmacistRepository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException("Pharmacist not found with id " + id));

        existingPharmacist.setFirstName(pharmacistDTO.getFirstName());
        existingPharmacist.setLastName(pharmacistDTO.getLastName());
        existingPharmacist.setPhoneNumber(pharmacistDTO.getPhoneNumber());
        existingPharmacist.setEmail(pharmacistDTO.getEmail());
        existingPharmacist.setShiftTiming(pharmacistDTO.getShiftTiming());

        Pharmacist updatedPharmacist = pharmacistRepository.save(existingPharmacist);
        return modelMapper.map(updatedPharmacist, PharmacistDTO.class);
    }

    @Override
    public void deletePharmacist(int id) {
        if (!pharmacistRepository.existsById(id)) {
            throw new RequestNotFoundException("Pharmacist not found with id " + id);
        }
        pharmacistRepository.deleteById(id);
    }
}
