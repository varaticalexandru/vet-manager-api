package org.alexv.vet_manager_api.service.service;

import org.alexv.vet_manager_api.service.domain.dto.ServicesDTO;
import org.alexv.vet_manager_api.service.domain.entity.Service;

import java.util.List;

public interface ServiceService {
    ServicesDTO getAllServices();
}
