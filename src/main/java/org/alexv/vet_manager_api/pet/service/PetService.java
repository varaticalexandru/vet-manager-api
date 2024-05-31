package org.alexv.vet_manager_api.pet.service;

import org.alexv.vet_manager_api.pet.domain.dto.PetsDTO;
import org.alexv.vet_manager_api.pet.domain.entity.Pet;

import java.util.List;

public interface PetService {
    PetsDTO getAllPets();
}
