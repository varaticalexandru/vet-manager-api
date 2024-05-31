package org.alexv.vet_manager_api.appointment.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.vet_manager_api.appointment.domain.dto.AppointmentsDTO;
import org.alexv.vet_manager_api.appointment.domain.repository.AppointmentJpaRepository;
import org.alexv.vet_manager_api.appointment.mapper.impl.AppointmentMapper;
import org.alexv.vet_manager_api.appointment.service.AppointmentService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppointmentServiceImpl implements AppointmentService {

    AppointmentJpaRepository repository;
    AppointmentMapper appointmentMapper;

    @Override
    public AppointmentsDTO getAllAppointments() {
        return AppointmentsDTO.builder()
                .appointments(
                        repository
                                .findAll()
                                .stream()
                                .map(appointmentMapper::mapTo)
                                .toList()
                )
                .build();
    }
}
