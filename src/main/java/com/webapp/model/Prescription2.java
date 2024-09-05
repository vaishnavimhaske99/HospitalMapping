package com.webapp.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "Prescriptions")
public class Prescription2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int prescriptionID;

    @ManyToOne
    @JoinColumn(name = "doctorID")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patientID")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "medicineID")
    private Medicine medicine;

    @ManyToOne
    @JoinColumn(name = "pharmacistID")
    private Pharmacist pharmacist;

    @ManyToOne
    @JoinColumn(name = "appointmentID")
    private Appointment appointment;

    private int quantity;
    private String dosage;
    private LocalDateTime prescriptionDate;


}
