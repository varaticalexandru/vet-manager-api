package org.alexv.vet_manager_api.appointment.service;

import org.alexv.vet_manager_api.appointment.domain.dto.NewAppointmentDTO;
import org.alexv.vet_manager_api.appointment.domain.dto.AppointmentDTO;
import org.alexv.vet_manager_api.appointment.domain.dto.AppointmentUpdateDTO;
import org.alexv.vet_manager_api.appointment.domain.dto.AppointmentsDTO;
import org.alexv.vet_manager_api.appointment.domain.entity.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


public interface AppointmentService {

    AppointmentsDTO getAllAppointments();
    Page<AppointmentDTO> getAppointmentsByPageable(Pageable pageable);
    Page<AppointmentDTO> getAppointmentsByPageableAndSpec(Specification<Appointment> spec, Pageable pageable);
    AppointmentDTO updateAppointment(Long id, AppointmentUpdateDTO appointmentDTO);
    AppointmentDTO addAppointment(NewAppointmentDTO newAppointmentDTO);
}
