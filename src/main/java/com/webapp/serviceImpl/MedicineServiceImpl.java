// MedicineServiceImpl.java
package com.webapp.serviceImpl;

import com.webapp.dto.MedicineDTO;
import com.webapp.model.Medicine;
import com.webapp.repository.MedicineRepository;
import com.webapp.service.MedicineService;
import com.webapp.exception.InvalidRequestException;
import com.webapp.exception.RequestNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<MedicineDTO> getAllMedicines() {
        return medicineRepository.findAll().stream()
                .map(medicine -> modelMapper.map(medicine, MedicineDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public MedicineDTO getMedicineById(int id) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException("Medicine not found with id " + id));
        return modelMapper.map(medicine, MedicineDTO.class);
    }

    @Override
    public MedicineDTO createMedicine(MedicineDTO medicineDTO) {
        if (medicineDTO == null) {
            throw new InvalidRequestException("Invalid Medicine details provided");
        }
        Medicine medicine = modelMapper.map(medicineDTO, Medicine.class);
        Medicine createdMedicine = medicineRepository.save(medicine);
        return modelMapper.map(createdMedicine, MedicineDTO.class);
    }

    @Override
    public MedicineDTO updateMedicine(int id, MedicineDTO medicineDTO) {
        if (medicineDTO == null) {
            throw new InvalidRequestException("Invalid Medicine details provided");
        }
        Medicine existingMedicine = medicineRepository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException("Medicine not found with id " + id));

        existingMedicine.setName(medicineDTO.getName());
        existingMedicine.setType(medicineDTO.getType());
        existingMedicine.setPrice(medicineDTO.getPrice());
        existingMedicine.setStockQuantity(medicineDTO.getStockQuantity());
        existingMedicine.setExpirationDate(medicineDTO.getExpirationDate());

        Medicine updatedMedicine = medicineRepository.save(existingMedicine);
        return modelMapper.map(updatedMedicine, MedicineDTO.class);
    }

    @Override
    public void deleteMedicine(int id) {
        if (!medicineRepository.existsById(id)) {
            throw new RequestNotFoundException("Medicine not found with id " + id);
        }
        medicineRepository.deleteById(id);
    }
}
