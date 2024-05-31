package org.alexv.vet_manager_api.appointment.service;

import org.alexv.vet_manager_api.appointment.domain.dto.AppointmentsDTO;


public interface AppointmentService {
    AppointmentsDTO getAllAppointments();
}
