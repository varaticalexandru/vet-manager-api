package org.alexv.vet_manager_api.appointment.domain.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.alexv.vet_manager_api.appointment.domain.entity.AppointmentStatus;
import org.alexv.vet_manager_api.service.domain.dto.NewServiceDTO;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdAppointmentDTO {

    Long id;
    Instant date;
    Boolean newPet;
    Object pet;
    Boolean newDoctor;
    Object doctor;
    List<Long> services;
    List<NewServiceDTO> newServices;
    AppointmentStatus status;
    String diagnostic;
}
