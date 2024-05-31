package org.alexv.vet_manager_api.pet.mapper.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.vet_manager_api.commons.mapper.Mapper;
import org.alexv.vet_manager_api.pet.domain.dto.PetDTO;
import org.alexv.vet_manager_api.pet.domain.entity.Pet;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetMapper implements Mapper<Pet, PetDTO> {

    ModelMapper modelMapper;

    @Override
    public PetDTO mapTo(Pet pet) {
        return modelMapper.map(pet, PetDTO.class);
    }

    @Override
    public Pet mapFrom(PetDTO petDTO) {
        return modelMapper.map(petDTO, Pet.class);
    }

    @Override
    public List<PetDTO> mapTo(List<Pet> a) {
        return a
                .stream()
                .map(pet -> modelMapper.map(pet, PetDTO.class))
                .toList();
    }

    @Override
    public List<Pet> mapFrom(List<PetDTO> b) {
        return b
                .stream()
                .map(petDTO -> modelMapper.map(petDTO, Pet.class))
                .toList();
    }
}
