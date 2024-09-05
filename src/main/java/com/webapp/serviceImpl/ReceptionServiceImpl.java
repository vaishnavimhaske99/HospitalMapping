// ReceptionServiceImpl.java
package com.webapp.serviceImpl;

import com.webapp.dto.ReceptionDTO;
import com.webapp.model.*;
import com.webapp.repository.*;
import com.webapp.service.ReceptionService;
import com.webapp.exception.InvalidRequestException;
import com.webapp.exception.RequestNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReceptionServiceImpl implements ReceptionService {

    @Autowired
    private ReceptionRepository receptionRepository;

    @Autowired
    private UserAuthenticationRepository userAuthenticationRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ReceptionDTO> getAllReceptions() {
        return receptionRepository.findAll().stream()
                .map(reception -> modelMapper.map(reception, ReceptionDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ReceptionDTO getReceptionById(int id) {
        Reception reception = receptionRepository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException("Reception not found with id " + id));
        return modelMapper.map(reception, ReceptionDTO.class);
    }

    @Override
    public ReceptionDTO createReception(ReceptionDTO receptionDTO) {
        if (receptionDTO == null) {
            throw new InvalidRequestException("Invalid Reception details provided");
        }

        UserAuthentication user = userAuthenticationRepository.findById(receptionDTO.getStaffID())
                .orElseThrow(() -> new RequestNotFoundException("User not found with id " + receptionDTO.getStaffID()));
        Patient patient = patientRepository.findById(receptionDTO.getPatientID())
                .orElseThrow(() -> new RequestNotFoundException("Patient not found with id " + receptionDTO.getPatientID()));
        Appointment appointment = appointmentRepository.findById(receptionDTO.getAppointmentID())
                .orElseThrow(() -> new RequestNotFoundException("Appointment not found with id " + receptionDTO.getAppointmentID()));

        Reception reception = modelMapper.map(receptionDTO, Reception.class);
        reception.setUser(user);
        reception.setPatient(patient);
        reception.setAppointment(appointment);

        Reception createdReception = receptionRepository.save(reception);
        return modelMapper.map(createdReception, ReceptionDTO.class);
    }

    @Override
    public ReceptionDTO updateReception(int id, ReceptionDTO receptionDTO) {
        if (receptionDTO == null) {
            throw new InvalidRequestException("Invalid Reception details provided");
        }

        Reception existingReception = receptionRepository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException("Reception not found with id " + id));

        UserAuthentication user = userAuthenticationRepository.findById(receptionDTO.getStaffID())
                .orElseThrow(() -> new RequestNotFoundException("User not found with id " + receptionDTO.getStaffID()));
        Patient patient = patientRepository.findById(receptionDTO.getPatientID())
                .orElseThrow(() -> new RequestNotFoundException("Patient not found with id " + receptionDTO.getPatientID()));
        Appointment appointment = appointmentRepository.findById(receptionDTO.getAppointmentID())
                .orElseThrow(() -> new RequestNotFoundException("Appointment not found with id " + receptionDTO.getAppointmentID()));

        existingReception.setUser(user);
        existingReception.setPatient(patient);
        existingReception.setAppointment(appointment);
        existingReception.setCheckInTime(receptionDTO.getCheckInTime());
        existingReception.setCheckOutTime(receptionDTO.getCheckOutTime());

        Reception updatedReception = receptionRepository.save(existingReception);
        return modelMapper.map(updatedReception, ReceptionDTO.class);
    }

    @Override
    public void deleteReception(int id) {
        if (!receptionRepository.existsById(id)) {
            throw new RequestNotFoundException("Reception not found with id " + id);
        }
        receptionRepository.deleteById(id);
    }
}
