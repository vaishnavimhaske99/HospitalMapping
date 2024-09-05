// PatientDTO.java
package com.webapp.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class PatientDTO {
    private int patientID;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String bloodgroup;
    private String gender;
    private String address;
    private String phoneNumber;
    private String email;
    private String medicalHistory;
}
