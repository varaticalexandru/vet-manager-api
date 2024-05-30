package org.alexv.vet_manager_api.service.domain.repository;

import org.alexv.vet_manager_api.service.domain.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceJpaRepository extends JpaRepository<Service, Long> {
}
