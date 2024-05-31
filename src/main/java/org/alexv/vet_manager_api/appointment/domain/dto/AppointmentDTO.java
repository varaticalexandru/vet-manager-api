package org.alexv.vet_manager_api.appointment.domain.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.alexv.vet_manager_api.appointment.domain.entity.AppointmentStatus;
import org.alexv.vet_manager_api.doctor.domain.dto.DoctorDTO;
import org.alexv.vet_manager_api.pet.domain.dto.PetDTO;
import org.alexv.vet_manager_api.service.domain.dto.ServiceDTO;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppointmentDTO {

    Long id;
    Instant date;
    String diagnostic;
    PetDTO pet;
    AppointmentStatus status;
    DoctorDTO doctor;
    List<ServiceDTO> services = new ArrayList<>();
    Double totalCost;
}
