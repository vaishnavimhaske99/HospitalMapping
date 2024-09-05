// AppointmentService.java
package com.webapp.service;

import com.webapp.dto.AppointmentDTO;
import java.util.List;

public interface AppointmentService {
    List<AppointmentDTO> getAllAppointments();
    AppointmentDTO getAppointmentById(int id);
    AppointmentDTO createAppointment(AppointmentDTO appointmentDTO);
    AppointmentDTO updateAppointment(int id, AppointmentDTO appointmentDTO);
    void deleteAppointment(int id);
}
