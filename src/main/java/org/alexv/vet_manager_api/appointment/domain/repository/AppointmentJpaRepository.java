package org.alexv.vet_manager_api.appointment.domain.repository;

import org.alexv.vet_manager_api.appointment.domain.dto.AppointmentDTO;
import org.alexv.vet_manager_api.appointment.domain.entity.Appointment;
import org.alexv.vet_manager_api.appointment.domain.entity.AppointmentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentJpaRepository extends JpaRepository<Appointment, Long>, JpaSpecificationExecutor<Appointment> {

    List<Appointment> findAllByStatus(AppointmentStatus status);

}
