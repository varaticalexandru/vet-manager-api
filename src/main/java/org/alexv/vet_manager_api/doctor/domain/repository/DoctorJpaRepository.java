package org.alexv.vet_manager_api.doctor.domain.repository;

import org.alexv.vet_manager_api.doctor.domain.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorJpaRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByFirstNameAndLastName(String firstName, String lastName);
}
