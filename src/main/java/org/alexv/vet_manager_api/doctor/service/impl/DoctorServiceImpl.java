package org.alexv.vet_manager_api.doctor.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.vet_manager_api.doctor.domain.dto.DoctorsDTO;
import org.alexv.vet_manager_api.doctor.domain.repository.DoctorJpaRepository;
import org.alexv.vet_manager_api.doctor.mapper.impl.DoctorMapper;
import org.alexv.vet_manager_api.doctor.service.DoctorService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DoctorServiceImpl implements DoctorService {

    DoctorJpaRepository repository;
    DoctorMapper doctorMapper;

    @Override
    public DoctorsDTO getAllDoctors() {
        return DoctorsDTO.builder()
                .doctors(
                        repository
                                .findAll()
                                .stream()
                                .map(doctorMapper::mapTo)
                                .toList())

                .build();
    }
}
