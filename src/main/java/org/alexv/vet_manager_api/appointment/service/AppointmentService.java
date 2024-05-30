package org.alexv.vet_manager_api.appointment.service;

import org.alexv.vet_manager_api.appointment.domain.entity.Appointment;

import java.util.List;

public interface AppointmentService {
    List<Appointment> getAllAppointments();
}
