package org.alexv.vet_manager_api.doctor.mapper.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.vet_manager_api.commons.mapper.Mapper;
import org.alexv.vet_manager_api.doctor.domain.dto.DoctorDTO;
import org.alexv.vet_manager_api.doctor.domain.entity.Doctor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DoctorMapper implements Mapper<Doctor, DoctorDTO> {

    ModelMapper modelMapper;

    @Override
    public DoctorDTO toDTO(Doctor doctor) {
        return modelMapper.map(doctor, DoctorDTO.class);
    }

    @Override
    public Doctor toEntity(DoctorDTO doctorDTO) {
        return modelMapper.map(doctorDTO, Doctor.class);
    }

    @Override
    public List<DoctorDTO> toDTO(List<Doctor> a) {
        return a
                .stream()
                .map(doctor -> modelMapper.map(doctor, DoctorDTO.class))
                .toList();
    }

    @Override
    public List<Doctor> toEntity(List<DoctorDTO> b) {
        return b
                .stream()
                .map(doctorDTO -> modelMapper.map(doctorDTO, Doctor.class))
                .toList();
    }
}
