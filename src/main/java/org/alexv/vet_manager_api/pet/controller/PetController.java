package org.alexv.vet_manager_api.pet.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.vet_manager_api.pet.domain.dto.PetsDTO;
import org.alexv.vet_manager_api.pet.service.PetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/pets")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetController {

    PetService petService;

    @GetMapping()
    public PetsDTO getAllPets() {
        return petService.getAllPets();
    }


}
