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
public class NewAppointmentDTO {

    Object pet;
    Object doctor;
    Boolean newPet;
    Boolean newDoctor;
    Instant date;
    AppointmentStatus status;
    String diagnostic;
    List<Long> services;
    List<NewServiceDTO> newServices;
}
