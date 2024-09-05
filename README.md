# Hospital Management System

## Overview

This Spring Boot project is designed to manage various aspects of a hospital, including appointments, patient records, prescriptions, and user authentication. It uses JPA with Hibernate for ORM and MySQL for the database. The project also incorporates Lombok for boilerplate code reduction.

## Entities

### Appointment

- **appointmentID**: Unique identifier
- **patient**: Linked `Patient`
- **doctor**: Linked `Doctor`
- **appointmentDate**: Date of the appointment
- **appointmentTime**: Time of the appointment
- **status**: Status of the appointment (e.g., scheduled, completed)

### UserAuthentication

- **userID**: Unique identifier
- **username**: Unique username for login
- **passwordHash**: Hashed password
- **role**: User role (`ADMIN`, `DOCTOR`, `RECEPTIONIST`, `PATIENT`)
- **lastLoginTime**: Timestamp of the last login

### Role Enum

- **ADMIN**: Administrator with full access
- **DOCTOR**: Doctor with permissions to manage appointments and prescriptions
- **RECEPTIONIST**: Receptionist with permissions to handle patient check-ins and appointments
- **PATIENT**: Patient with access to their own information and appointments

### Reception

- **receptionID**: Unique identifier
- **user**: Linked `UserAuthentication` (staff)
- **patient**: Linked `Patient`
- **appointment**: Linked `Appointment`
- **checkInTime**: Time the patient checked in
- **checkOutTime**: Time the patient checked out

### Prescription

- **prescriptionID**: Unique identifier
- **doctor**: Linked `Doctor`
- **patient**: Linked `Patient`
- **medicine**: Linked `Medicine`
- **pharmacist**: Linked `Pharmacist`
- **appointment**: Linked `Appointment`
- **quantity**: Quantity of medicine prescribed
- **dosage**: Dosage instructions
- **prescriptionDate**: Date of the prescription

### Pharmacist

- **pharmacistID**: Unique identifier
- **firstName**: First name
- **lastName**: Last name
- **phoneNumber**: Contact phone number
- **email**: Contact email
- **shiftTiming**: Shift timing of the pharmacist

## Entity Relationships

- **Appointment**: Linked to `Patient` and `Doctor`, and contains `appointmentDate` and `appointmentTime`.
- **UserAuthentication**: Provides authentication details with a `Role` that determines user permissions.
- **Reception**: Manages patient check-ins and check-outs, linked to `UserAuthentication`, `Patient`, and `Appointment`.
- **Prescription**: Represents a medicine prescription, linked to `Doctor`, `Patient`, `Medicine`, `Pharmacist`, and `Appointment`.
- **Pharmacist**: Represents the pharmacist handling prescriptions, with details about their shift and contact information.

## Database Schema

The database schema is designed to reflect the entity relationships and constraints as described. Each entity is mapped to a corresponding table in the MySQL database.

## API Endpoints

The project includes RESTful endpoints for managing various entities. For each entity, CRUD operations are available.

### Appointments

- **Get Appointment by ID**: `GET /appointments/{id}`
- **Create Appointment**: `POST /appointments`
- **Update Appointment**: `PUT /appointments/{id}`
- **Delete Appointment**: `DELETE /appointments/{id}`

### User Authentication

- **Get User by ID**: `GET /users/{id}`
- **Create User**: `POST /users`
- **Update User**: `PUT /users/{id}`
- **Delete User**: `DELETE /users/{id}`

### Receptions

- **Get Reception by ID**: `GET /receptions/{id}`
- **Create Reception**: `POST /receptions`
- **Update Reception**: `PUT /receptions/{id}`
- **Delete Reception**: `DELETE /receptions/{id}`

### Prescriptions

- **Get Prescription by ID**: `GET /prescriptions/{id}`
- **Create Prescription**: `POST /prescriptions`
- **Update Prescription**: `PUT /prescriptions/{id}`
- **Delete Prescription**: `DELETE /prescriptions/{id}`

### Pharmacists

- **Get Pharmacist by ID**: `GET /pharmacists/{id}`
- **Create Pharmacist**: `POST /pharmacists`
- **Update Pharmacist**: `PUT /pharmacists/{id}`
- **Delete Pharmacist**: `DELETE /pharmacists/{id}`

## ModelMapper Configuration

### Overview

ModelMapper is used to facilitate the conversion between entity objects and Data Transfer Objects (DTOs). This reduces the need for manual conversion logic and helps keep the codebase clean and maintainable.

### Configuration

ModelMapper is configured in the `AppConfig` class:

```java
package com.webapp.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
