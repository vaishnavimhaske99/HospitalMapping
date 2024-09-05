// PharmacistDTO.java
package com.webapp.dto;

import lombok.Data;

@Data
public class PharmacistDTO {
    private int pharmacistID;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String shiftTiming;
}
