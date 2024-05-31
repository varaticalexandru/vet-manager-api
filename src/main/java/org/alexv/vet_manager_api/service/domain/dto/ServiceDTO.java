package org.alexv.vet_manager_api.service.domain.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.alexv.vet_manager_api.service.domain.entity.Price;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceDTO {

    Long id;
    String name;
    Price price;
}
