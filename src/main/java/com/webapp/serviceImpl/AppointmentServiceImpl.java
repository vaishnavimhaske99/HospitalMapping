// AppointmentServiceImpl.java
package com.webapp.serviceImpl;

import com.webapp.dto.AppointmentDTO;
import com.webapp.model.Appointment;
import com.webapp.repository.AppointmentRepository;
import com.webapp.service.AppointmentService;
import com.webapp.exception.InvalidRequestException;
import com.webapp.exception.RequestNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<AppointmentDTO> getAllAppointments() {
        return appointmentRepository.findAll().stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO getAppointmentById(int id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException("Appointment not found with id " + id));
        return modelMapper.map(appointment, AppointmentDTO.class);
    }

    @Override
    public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO) {
        if (appointmentDTO == null) {
            throw new InvalidRequestException("Invalid Appointment details provided");
        }
        Appointment appointment = modelMapper.map(appointmentDTO, Appointment.class);
        Appointment createdAppointment = appointmentRepository.save(appointment);
        return modelMapper.map(createdAppointment, AppointmentDTO.class);
    }

    @Override
    public AppointmentDTO updateAppointment(int id, AppointmentDTO appointmentDTO) {
        if (appointmentDTO == null) {
            throw new InvalidRequestException("Invalid Appointment details provided");
        }
        Appointment existingAppointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException("Appointment not found with id " + id));

        existingAppointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
        existingAppointment.setAppointmentTime(appointmentDTO.getAppointmentTime());
        existingAppointment.setStatus(appointmentDTO.getStatus());
        // Set patient and doctor as well, if needed, depending on your implementation

        Appointment updatedAppointment = appointmentRepository.save(existingAppointment);
        return modelMapper.map(updatedAppointment, AppointmentDTO.class);
    }

    @Override
    public void deleteAppointment(int id) {
        if (!appointmentRepository.existsById(id)) {
            throw new RequestNotFoundException("Appointment not found with id " + id);
        }
        appointmentRepository.deleteById(id);
    }
}
