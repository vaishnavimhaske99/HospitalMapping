// DoctorDTO.java
package com.webapp.dto;

import lombok.Data;

@Data
public class DoctorDTO {
    private int doctorID;
    private String firstName;
    private String lastName;
    private String specialization;
    private String phoneNumber;
    private String email;
    private String officeAddress;
}
