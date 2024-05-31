package org.alexv.vet_manager_api.service.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.vet_manager_api.service.domain.dto.ServicesDTO;
import org.alexv.vet_manager_api.service.domain.repository.ServiceJpaRepository;
import org.alexv.vet_manager_api.service.mapper.impl.ServiceMapper;
import org.alexv.vet_manager_api.service.service.ServiceService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ServiceServiceImpl implements ServiceService {

    ServiceJpaRepository repository;
    ServiceMapper serviceMapper;

    @Override
    public ServicesDTO getAllServices() {
        return ServicesDTO.builder()
                .services(
                        repository
                                .findAll()
                                .stream()
                                .map(serviceMapper::toDTO)
                                .toList()
                )
                .build();
    }
}
