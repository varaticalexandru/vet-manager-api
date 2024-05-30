package org.alexv.vet_manager_api.service.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.vet_manager_api.service.domain.repository.ServiceJpaRepository;
import org.alexv.vet_manager_api.service.service.ServiceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ServiceServiceImpl implements ServiceService {

    ServiceJpaRepository repository;

    @Override
    public List<org.alexv.vet_manager_api.service.domain.entity.Service> getAllServices() {
        return repository.findAll();
    }
}
