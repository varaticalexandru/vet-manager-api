package org.alexv.vet_manager_api.pet.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.vet_manager_api.pet.domain.entity.Pet;
import org.alexv.vet_manager_api.pet.domain.repository.PetJpaRepository;
import org.alexv.vet_manager_api.pet.service.PetService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetServiceImpl implements PetService {

    PetJpaRepository repository;

    @Override
    public List<Pet> getAllPets() {
        return repository.findAll();
    }
}
