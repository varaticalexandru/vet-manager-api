package org.alexv.vet_manager_api.appointment.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.vet_manager_api.appointment.domain.dto.AppointmentsDTO;
import org.alexv.vet_manager_api.appointment.service.AppointmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppointmentController {

    AppointmentService appointmentService;

    @GetMapping()
    public AppointmentsDTO getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

}
