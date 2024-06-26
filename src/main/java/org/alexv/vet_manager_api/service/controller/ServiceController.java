package org.alexv.vet_manager_api.service.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.vet_manager_api.service.domain.dto.ServicesDTO;
import org.alexv.vet_manager_api.service.service.ServiceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ServiceController {

    ServiceService serviceService;

    @GetMapping()
    public ServicesDTO getAllServices() {
        return serviceService.getAllServices();
    }


}
