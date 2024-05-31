package org.alexv.vet_manager_api.appointment.mapper.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.vet_manager_api.appointment.domain.dto.AppointmentDTO;
import org.alexv.vet_manager_api.appointment.domain.entity.Appointment;
import org.alexv.vet_manager_api.commons.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppointmentMapper implements Mapper<Appointment, AppointmentDTO> {

    ModelMapper modelMapper;

    @Override
    public AppointmentDTO mapTo(Appointment appointment) {
        return modelMapper.map(appointment, AppointmentDTO.class);
    }

    @Override
    public Appointment mapFrom(AppointmentDTO appointmentDTO) {
        return modelMapper.map(appointmentDTO, Appointment.class);
    }

    @Override
    public List<AppointmentDTO> mapTo(List<Appointment> a) {
        return a
                .stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentDTO.class))
                .toList();
    }

    @Override
    public List<Appointment> mapFrom(List<AppointmentDTO> b) {
        return b
                .stream()
                .map(appointmentDTO -> modelMapper.map(appointmentDTO, Appointment.class))
                .toList();
    }
}
