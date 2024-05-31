package org.alexv.vet_manager_api.service.domain.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServicesDTO {
    List<ServiceDTO> services = new ArrayList<>();
}
