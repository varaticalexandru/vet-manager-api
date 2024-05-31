package org.alexv.vet_manager_api.appointment.mapper.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.alexv.vet_manager_api.appointment.domain.dto.AppointmentDTO;
import org.alexv.vet_manager_api.appointment.domain.entity.Appointment;
import org.alexv.vet_manager_api.commons.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.beans.factory.support.InstanceSupplier.using;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppointmentMapper implements Mapper<Appointment, AppointmentDTO> {

    ModelMapper modelMapper;

    @Autowired
    public AppointmentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        // total cost config
        modelMapper.addMappings(new PropertyMap<Appointment, AppointmentDTO>() {
            @Override
            protected void configure() {
                using(ctx -> ((Appointment) ctx.getSource()).getServices().stream()
                        .mapToDouble(service -> service.getPrice().getCost())
                        .sum()).map(source, destination.getTotalCost());
            }
        });
    }

    @Override
    public AppointmentDTO toDTO(Appointment appointment) {
        return modelMapper.map(appointment, AppointmentDTO.class);
    }

    @Override
    public Appointment toEntity(AppointmentDTO appointmentDTO) {
        return modelMapper.map(appointmentDTO, Appointment.class);
    }

    @Override
    public List<AppointmentDTO> toDTO(List<Appointment> a) {
        return a
                .stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentDTO.class))
                .toList();
    }

    @Override
    public List<Appointment> toEntity(List<AppointmentDTO> b) {
        return b
                .stream()
                .map(appointmentDTO -> modelMapper.map(appointmentDTO, Appointment.class))
                .toList();
    }
}
