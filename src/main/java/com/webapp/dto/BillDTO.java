package com.webapp.dto;

import lombok.Data;


@Data
public class BillDTO {
    private int billID;
    private int patientID;
    private int appointmentID;
    private double totalAmount;
    private String paymentStatus;
    private String paymentMethod;
}
