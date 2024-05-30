package org.alexv.vet_manager_api.appointment.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.vet_manager_api.appointment.domain.entity.Appointment;
import org.alexv.vet_manager_api.appointment.domain.repository.AppointmentJpaRepository;
import org.alexv.vet_manager_api.appointment.service.AppointmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppointmentServiceImpl implements AppointmentService {

    AppointmentJpaRepository repository;

    @Override
    public List<Appointment> getAllAppointments() {
        return repository.findAll();
    }
}
