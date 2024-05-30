package org.alexv.vet_manager_api.doctor.domain.repository;

import org.alexv.vet_manager_api.doctor.domain.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorJpaRepository extends JpaRepository<Doctor, Long> {
}
