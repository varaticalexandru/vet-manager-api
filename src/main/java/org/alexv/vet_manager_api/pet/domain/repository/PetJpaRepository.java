package org.alexv.vet_manager_api.pet.domain.repository;

import org.alexv.vet_manager_api.pet.domain.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetJpaRepository extends JpaRepository<Pet, Long> {
}
