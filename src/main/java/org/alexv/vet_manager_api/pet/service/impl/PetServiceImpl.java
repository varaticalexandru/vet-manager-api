package org.alexv.vet_manager_api.pet.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.vet_manager_api.pet.domain.dto.PetsDTO;
import org.alexv.vet_manager_api.pet.domain.repository.PetJpaRepository;
import org.alexv.vet_manager_api.pet.mapper.impl.PetMapper;
import org.alexv.vet_manager_api.pet.service.PetService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetServiceImpl implements PetService {

    PetJpaRepository repository;
    PetMapper petMapper;

    @Override
    public PetsDTO getAllPets() {
        return PetsDTO.builder()
                .pets(
                        repository
                                .findAll()
                                .stream()
                                .map(petMapper::mapTo)
                                .toList())
                .build();
    }
}
