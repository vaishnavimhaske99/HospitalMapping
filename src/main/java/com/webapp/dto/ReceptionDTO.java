// ReceptionDTO.java
package com.webapp.dto;

import java.time.LocalDateTime;

public class ReceptionDTO {

    private int receptionID;
    private int staffID;
    private int patientID;
    private int appointmentID;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;

    // Getters and Setters
    public int getReceptionID() {
        return receptionID;
    }

    public void setReceptionID(int receptionID) {
        this.receptionID = receptionID;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

  
}