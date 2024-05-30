package org.alexv.vet_manager_api.doctor.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.vet_manager_api.doctor.domain.entity.Doctor;
import org.alexv.vet_manager_api.doctor.domain.repository.DoctorJpaRepository;
import org.alexv.vet_manager_api.doctor.service.DoctorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DoctorServiceImpl implements DoctorService {

    DoctorJpaRepository repository;

    @Override
    public List<Doctor> getAllDoctors() {
        return repository.findAll();
    }
}
