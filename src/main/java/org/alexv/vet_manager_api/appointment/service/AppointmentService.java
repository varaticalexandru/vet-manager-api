package org.alexv.vet_manager_api.appointment.service;

import org.alexv.vet_manager_api.appointment.domain.dto.AppointmentDTO;
import org.alexv.vet_manager_api.appointment.domain.dto.AppointmentsDTO;
import org.alexv.vet_manager_api.appointment.domain.entity.Appointment;

import java.util.List;

public interface AppointmentService {
    AppointmentsDTO getAllAppointments();
}
