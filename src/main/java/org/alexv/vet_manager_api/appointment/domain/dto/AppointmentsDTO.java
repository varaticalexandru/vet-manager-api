package org.alexv.vet_manager_api.appointment.domain.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppointmentsDTO {
    List<AppointmentDTO> appointments = new ArrayList<>();
}
