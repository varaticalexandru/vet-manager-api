package org.alexv.vet_manager_api.appointment.domain.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.alexv.vet_manager_api.appointment.domain.entity.AppointmentStatus;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppointmentUpdateDTO {

    Long id;
    String petName;
    String doctorFirstName;
    String doctorLastName;
    Instant date;
    AppointmentStatus status;
    String diagnostic;
}
