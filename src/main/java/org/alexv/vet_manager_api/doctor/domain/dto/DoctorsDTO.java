package org.alexv.vet_manager_api.doctor.domain.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DoctorsDTO {
    List<DoctorDTO> doctors = new ArrayList<>();
}
