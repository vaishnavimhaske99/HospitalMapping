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
@Table(name = "Reception")
public class Reception {
    @Id
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int receptionID;

    @ManyToOne
    @JoinColumn(name = "staffID")
    private UserAuthentication user;

    @ManyToOne
    @JoinColumn(name = "patientID")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "appointmentID")
    private Appointment appointment;

    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;

}
