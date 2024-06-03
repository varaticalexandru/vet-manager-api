package org.alexv.vet_manager_api.appointment.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.vet_manager_api.appointment.domain.dto.NewAppointmentDTO;
import org.alexv.vet_manager_api.appointment.domain.dto.AppointmentDTO;
import org.alexv.vet_manager_api.appointment.domain.dto.AppointmentUpdateDTO;
import org.alexv.vet_manager_api.appointment.domain.dto.AppointmentsDTO;
import org.alexv.vet_manager_api.appointment.domain.entity.Appointment;
import org.alexv.vet_manager_api.appointment.domain.repository.specification.AppointmentSpecification;
import org.alexv.vet_manager_api.appointment.service.AppointmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;


@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppointmentController {

    AppointmentService appointmentService;

    @GetMapping("/all")
    public AppointmentsDTO getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping()
    public Page<AppointmentDTO> getAppointmentsByFiltersAndPageable(
            Pageable pageable,
            @RequestParam(required = false) String petName,
            @RequestParam(required = false) String doctorFirstName,
            @RequestParam(required = false) String doctorLastName,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Instant date,
            @RequestParam(required = false) String diagnostic,
            @RequestParam(required = false) Double totalCost
    ) {
        Specification<Appointment> spec = Specification.where(null);

        if (petName != null && !petName.isEmpty()) {
            spec = spec.and(AppointmentSpecification.filterByPetName(petName));
        }
        if (doctorFirstName != null && !doctorFirstName.isEmpty()) {
            spec = spec.and(AppointmentSpecification.filterByDoctorFirstName(doctorFirstName));
        }
        if (doctorLastName != null && !doctorLastName.isEmpty()) {
            spec = spec.and(AppointmentSpecification.filterByDoctorLastName(doctorLastName));
        }
        if (status != null && !status.isEmpty()) {
            spec = spec.and(AppointmentSpecification.filterByStatus(status));
        }
        if (date != null) {
            spec = spec.and(AppointmentSpecification.filterByDate(date));
        }
        if (diagnostic != null && !diagnostic.isEmpty()) {
            spec = spec.and(AppointmentSpecification.filterByDiagnostic(diagnostic));
        }
        if (totalCost != null) {
            spec = spec.and(AppointmentSpecification.filterByTotalCost(totalCost));
        }

        return appointmentService.getAppointmentsByPageableAndSpec(spec, pageable);
    }


    @PutMapping("/{id}")
    public AppointmentDTO updateAppointment(
            @PathVariable Long id,
            @RequestBody AppointmentUpdateDTO appointmentDTO) {
        return appointmentService.updateAppointment(id, appointmentDTO);
    }

    @PostMapping
    public AppointmentDTO addAppointment(
            @RequestBody NewAppointmentDTO appointmentDTO
    ) {
        return appointmentService.addAppointment(appointmentDTO);
    }



}
