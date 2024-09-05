package com.webapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private int patientID;

    private String firstName;
    private String lastName;
    private java.time.LocalDate dob;
    private String bloodgroup;
    private String gender;
    private String address;
    private String phoneNumber;
    private String email;
    private String medicalHistory;

}
