package org.alexv.vet_manager_api.pet.domain.repository;

import org.alexv.vet_manager_api.pet.domain.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PetJpaRepository extends JpaRepository<Pet, Long> {
    Optional<Pet> findByName(String name);
}
