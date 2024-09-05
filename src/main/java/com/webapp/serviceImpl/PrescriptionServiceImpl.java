// PrescriptionServiceImpl.java
package com.webapp.serviceImpl;

import com.webapp.dto.PrescriptionDTO;
import com.webapp.model.*;
import com.webapp.repository.*;
import com.webapp.service.PrescriptionService;
import com.webapp.exception.RequestNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    private static final Logger logger = LoggerFactory.getLogger(PrescriptionServiceImpl.class);

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private PharmacistRepository pharmacistRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PrescriptionDTO> getAllPrescriptions() {
        logger.info("Fetching all prescriptions");
        return prescriptionRepository.findAll().stream()
                .map(prescription -> modelMapper.map(prescription, PrescriptionDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PrescriptionDTO getPrescriptionById(int id) {
        logger.info("Fetching prescription with id {}", id);
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException("Prescription not found with id " + id));
        return modelMapper.map(prescription, PrescriptionDTO.class);
    }

    @Override
    @Transactional
    public PrescriptionDTO createPrescription(PrescriptionDTO prescriptionDTO) {
        logger.info("Creating a new prescription");

        // Validate input
        validatePrescriptionDTO(prescriptionDTO);

        Doctor doctor = doctorRepository.findById(prescriptionDTO.getDoctorID())
                .orElseThrow(() -> new RequestNotFoundException("Doctor not found with id " + prescriptionDTO.getDoctorID()));
        Patient patient = patientRepository.findById(prescriptionDTO.getPatientID())
                .orElseThrow(() -> new RequestNotFoundException("Patient not found with id " + prescriptionDTO.getPatientID()));
        Medicine medicine = medicineRepository.findById(prescriptionDTO.getMedicineID())
                .orElseThrow(() -> new RequestNotFoundException("Medicine not found with id " + prescriptionDTO.getMedicineID()));
        Pharmacist pharmacist = pharmacistRepository.findById(prescriptionDTO.getPharmacistID())
                .orElseThrow(() -> new RequestNotFoundException("Pharmacist not found with id " + prescriptionDTO.getPharmacistID()));
        Appointment appointment = appointmentRepository.findById(prescriptionDTO.getAppointmentID())
                .orElseThrow(() -> new RequestNotFoundException("Appointment not found with id " + prescriptionDTO.getAppointmentID()));

        Prescription prescription = modelMapper.map(prescriptionDTO, Prescription.class);
        prescription.setDoctor(doctor);
        prescription.setPatient(patient);
        prescription.setMedicine(medicine);
        prescription.setPharmacist(pharmacist);
        prescription.setAppointment(appointment);

        Prescription createdPrescription = prescriptionRepository.save(prescription);
        logger.info("Prescription created with id {}", createdPrescription.getPrescriptionID());
        return modelMapper.map(createdPrescription, PrescriptionDTO.class);
    }

    @Override
    @Transactional
    public PrescriptionDTO updatePrescription(int id, PrescriptionDTO prescriptionDTO) {
        logger.info("Updating prescription with id {}", id);
        validatePrescriptionDTO(prescriptionDTO);

        Prescription existingPrescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException("Prescription not found with id " + id));

        Doctor doctor = doctorRepository.findById(prescriptionDTO.getDoctorID())
                .orElseThrow(() -> new RequestNotFoundException("Doctor not found with id " + prescriptionDTO.getDoctorID()));
        Patient patient = patientRepository.findById(prescriptionDTO.getPatientID())
                .orElseThrow(() -> new RequestNotFoundException("Patient not found with id " + prescriptionDTO.getPatientID()));
        Medicine medicine = medicineRepository.findById(prescriptionDTO.getMedicineID())
                .orElseThrow(() -> new RequestNotFoundException("Medicine not found with id " + prescriptionDTO.getMedicineID()));
        Pharmacist pharmacist = pharmacistRepository.findById(prescriptionDTO.getPharmacistID())
                .orElseThrow(() -> new RequestNotFoundException("Pharmacist not found with id " + prescriptionDTO.getPharmacistID()));
        Appointment appointment = appointmentRepository.findById(prescriptionDTO.getAppointmentID())
                .orElseThrow(() -> new RequestNotFoundException("Appointment not found with id " + prescriptionDTO.getAppointmentID()));

        existingPrescription.setDoctor(doctor);
        existingPrescription.setPatient(patient);
        existingPrescription.setMedicine(medicine);
        existingPrescription.setPharmacist(pharmacist);
        existingPrescription.setAppointment(appointment);
        existingPrescription.setQuantity(prescriptionDTO.getQuantity());
        existingPrescription.setDosage(prescriptionDTO.getDosage());
        existingPrescription.setPrescriptionDate(prescriptionDTO.getPrescriptionDate());

        Prescription updatedPrescription = prescriptionRepository.save(existingPrescription);
        logger.info("Prescription updated with id {}", updatedPrescription.getPrescriptionID());
        return modelMapper.map(updatedPrescription, PrescriptionDTO.class);
    }

    @Override
    public void deletePrescription(int id) {
        logger.info("Deleting prescription with id {}", id);
        if (!prescriptionRepository.existsById(id)) {
            throw new RequestNotFoundException("Prescription not found with id " + id);
        }
        prescriptionRepository.deleteById(id);
    }

    private void validatePrescriptionDTO(PrescriptionDTO prescriptionDTO) {
        // Implement any necessary validation logic here
    }
}
