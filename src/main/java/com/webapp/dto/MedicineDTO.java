// MedicineDTO.java
package com.webapp.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class MedicineDTO {
    private int medicineID;
    private String name;
    private String type;
    private double price;
    private int stockQuantity;
    private LocalDate expirationDate;
}
