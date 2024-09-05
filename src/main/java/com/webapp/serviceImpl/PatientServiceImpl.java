// PatientServiceImpl.java
package com.webapp.serviceImpl;

import com.webapp.dto.PatientDTO;
import com.webapp.model.Patient;
import com.webapp.repository.PatientRepository;
import com.webapp.service.PatientService;
import com.webapp.exception.InvalidRequestException;
import com.webapp.exception.RequestNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(patient -> modelMapper.map(patient, PatientDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PatientDTO getPatientById(int id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException("Patient not found with id " + id));
        return modelMapper.map(patient, PatientDTO.class);
    }

    @Override
    public PatientDTO createPatient(PatientDTO patientDTO) {
        if (patientDTO == null) {
            throw new InvalidRequestException("Invalid Patient details provided");
        }
        Patient patient = modelMapper.map(patientDTO, Patient.class);
        Patient createdPatient = patientRepository.save(patient);
        return modelMapper.map(createdPatient, PatientDTO.class);
    }

    @Override
    public PatientDTO updatePatient(int id, PatientDTO patientDTO) {
        if (patientDTO == null) {
            throw new InvalidRequestException("Invalid Patient details provided");
        }
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException("Patient not found with id " + id));

        existingPatient.setFirstName(patientDTO.getFirstName());
        existingPatient.setLastName(patientDTO.getLastName());
        existingPatient.setDob(patientDTO.getDob());
        existingPatient.setGender(patientDTO.getGender());
        existingPatient.setAddress(patientDTO.getAddress());
        existingPatient.setPhoneNumber(patientDTO.getPhoneNumber());
        existingPatient.setEmail(patientDTO.getEmail());
        existingPatient.setMedicalHistory(patientDTO.getMedicalHistory());

        Patient updatedPatient = patientRepository.save(existingPatient);
        return modelMapper.map(updatedPatient, PatientDTO.class);
    }

    @Override
    public void deletePatient(int id) {
        if (!patientRepository.existsById(id)) {
            throw new RequestNotFoundException("Patient not found with id " + id);
        }
        patientRepository.deleteById(id);
    }
}
