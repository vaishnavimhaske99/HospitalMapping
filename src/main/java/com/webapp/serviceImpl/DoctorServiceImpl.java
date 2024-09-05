// DoctorServiceImpl.java
package com.webapp.serviceImpl;

import com.webapp.dto.DoctorDTO;
import com.webapp.model.Doctor;
import com.webapp.repository.DoctorRepository;
import com.webapp.service.DoctorService;
import com.webapp.exception.InvalidRequestException;
import com.webapp.exception.RequestNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<DoctorDTO> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(doctor -> modelMapper.map(doctor, DoctorDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public DoctorDTO getDoctorById(int id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException("Doctor not found with id " + id));
        return modelMapper.map(doctor, DoctorDTO.class);
    }

    @Override
    public DoctorDTO createDoctor(DoctorDTO doctorDTO) {
        if (doctorDTO == null) {
            throw new InvalidRequestException("Invalid Doctor details provided");
        }
        Doctor doctor = modelMapper.map(doctorDTO, Doctor.class);
        Doctor createdDoctor = doctorRepository.save(doctor);
        return modelMapper.map(createdDoctor, DoctorDTO.class);
    }

    @Override
    public DoctorDTO updateDoctor(int id, DoctorDTO doctorDTO) {
        if (doctorDTO == null) {
            throw new InvalidRequestException("Invalid Doctor details provided");
        }
        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException("Doctor not found with id " + id));

        existingDoctor.setFirstName(doctorDTO.getFirstName());
        existingDoctor.setLastName(doctorDTO.getLastName());
        existingDoctor.setSpecialization(doctorDTO.getSpecialization());
        existingDoctor.setPhoneNumber(doctorDTO.getPhoneNumber());
        existingDoctor.setEmail(doctorDTO.getEmail());
        existingDoctor.setOfficeAddress(doctorDTO.getOfficeAddress());

        Doctor updatedDoctor = doctorRepository.save(existingDoctor);
        return modelMapper.map(updatedDoctor, DoctorDTO.class);
    }

    @Override
    public void deleteDoctor(int id) {
        if (!doctorRepository.existsById(id)) {
            throw new RequestNotFoundException("Doctor not found with id " + id);
        }
        doctorRepository.deleteById(id);
    }
}
