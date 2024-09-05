// PrescriptionDTO.java
package com.webapp.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PrescriptionDTO {
    private int prescriptionID;
    private int  doctorID;
    private int patientID;
    private int medicineID;
    private int pharmacistID;
    private int appointmentID;
    private int quantity;
    private String dosage;
    private LocalDateTime prescriptionDate;
}
