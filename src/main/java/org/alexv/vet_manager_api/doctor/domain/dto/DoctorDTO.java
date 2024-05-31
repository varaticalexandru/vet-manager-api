package org.alexv.vet_manager_api.doctor.domain.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DoctorDTO {

    Long id;
    String firstName;
    String lastName;
}
