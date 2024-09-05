// BillServiceImpl.java
package com.webapp.serviceImpl;

import com.webapp.dto.BillDTO;
import com.webapp.model.Appointment;
import com.webapp.model.Bill;
import com.webapp.model.Patient;
import com.webapp.repository.AppointmentRepository;
import com.webapp.repository.BillRepository;
import com.webapp.repository.PatientRepository;
import com.webapp.service.BillService;
import com.webapp.exception.InvalidRequestException;
import com.webapp.exception.RequestNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public List<BillDTO> getAllBills() {
        return billRepository.findAll().stream()
                .map(bill -> modelMapper.map(bill, BillDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public BillDTO getBillById(int id) {
        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException("Bill not found with id " + id));
        return modelMapper.map(bill, BillDTO.class);
    }

    @Override
    public BillDTO createBill(BillDTO billDTO) {
        if (billDTO == null) {
            throw new InvalidRequestException("Invalid Bill details provided");
        }

        // Check if related entities exist
        if (!patientRepository.existsById(billDTO.getPatientID())) {
            throw new RequestNotFoundException("Patient not found with id " + billDTO.getPatientID());
        }
        if (!appointmentRepository.existsById(billDTO.getAppointmentID())) {
            throw new RequestNotFoundException("Appointment not found with id " + billDTO.getAppointmentID());
        }

        // Map DTO to entity
        Bill bill = modelMapper.map(billDTO, Bill.class);

        // Save the bill
        Bill createdBill = billRepository.save(bill);

        return modelMapper.map(createdBill, BillDTO.class);
    }
    @Override
    public BillDTO updateBill(int id, BillDTO billDTO) {
        if (billDTO == null) {
            throw new InvalidRequestException("Invalid Bill details provided");
        }
        Bill existingBill = billRepository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException("Bill not found with id " + id));

        existingBill.setTotalAmount(billDTO.getTotalAmount());
        existingBill.setPaymentStatus(billDTO.getPaymentStatus());
        existingBill.setPaymentMethod(billDTO.getPaymentMethod());

        Bill updatedBill = billRepository.save(existingBill);
        return modelMapper.map(updatedBill, BillDTO.class);
    }

    @Override
    public void deleteBill(int id) {
        if (!billRepository.existsById(id)) {
            throw new RequestNotFoundException("Bill not found with id " + id);
        }
        billRepository.deleteById(id);
    }
}
