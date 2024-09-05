// ChargesDTO.java
package com.webapp.dto;

import lombok.Data;

@Data
public class ChargesDTO {
    private int chargesID;
    private int receptionID;
    private int pharmacistID;
    private int billID;
    private double amount;
    private String description;
}
