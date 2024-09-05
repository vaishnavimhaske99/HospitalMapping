package com.webapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Charges")
public class Charges {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chargesID;


    @ManyToOne
    @JoinColumn(name = "pharmacistID")
    private Pharmacist pharmacist;

    @ManyToOne
    @JoinColumn(name = "billID")
    private Bill bill;

    private double amount;
    private String description;

}
