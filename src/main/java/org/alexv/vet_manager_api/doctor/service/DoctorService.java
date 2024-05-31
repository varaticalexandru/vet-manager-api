package org.alexv.vet_manager_api.doctor.service;

import org.alexv.vet_manager_api.doctor.domain.dto.DoctorDTO;
import org.alexv.vet_manager_api.doctor.domain.dto.DoctorsDTO;

import java.util.List;

public interface DoctorService {

    DoctorsDTO getAllDoctors();
}
