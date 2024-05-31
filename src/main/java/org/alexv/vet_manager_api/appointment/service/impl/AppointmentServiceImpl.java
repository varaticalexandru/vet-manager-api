package org.alexv.vet_manager_api.appointment.service.impl;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.vet_manager_api.appointment.domain.dto.AppointmentDTO;
import org.alexv.vet_manager_api.appointment.domain.dto.AppointmentsDTO;
import org.alexv.vet_manager_api.appointment.domain.entity.Appointment;
import org.alexv.vet_manager_api.appointment.domain.repository.AppointmentJpaRepository;
import org.alexv.vet_manager_api.appointment.mapper.impl.AppointmentMapper;
import org.alexv.vet_manager_api.appointment.service.AppointmentService;
import org.alexv.vet_manager_api.commons.exception.ResourceNotFoundException;
import org.alexv.vet_manager_api.doctor.domain.entity.Doctor;
import org.alexv.vet_manager_api.doctor.domain.repository.DoctorJpaRepository;
import org.alexv.vet_manager_api.pet.domain.entity.Pet;
import org.alexv.vet_manager_api.pet.domain.repository.PetJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppointmentServiceImpl implements AppointmentService {

    AppointmentJpaRepository repository;
    AppointmentMapper appointmentMapper;
    AppointmentJpaRepository appointmentJpaRepository;
    PetJpaRepository petJpaRepository;
    DoctorJpaRepository doctorJpaRepository;


    @Override
    public AppointmentsDTO getAllAppointments() {

        return AppointmentsDTO.builder()
                .appointments(
                        repository
                                .findAll()
                                .stream()
                                .map(appointmentMapper::toDTO)
                                .toList()
                )
                .build();
    }

    @Override
    public Page<AppointmentDTO> getAppointmentsByPageable(Pageable pageable) {

        Page<Appointment> appointmentPage = repository.findAll(pageable);
        List<AppointmentDTO> appointmentDTOList = appointmentPage
                .stream()
                .map(appointmentMapper::toDTO)
                .toList();

        return new PageImpl<>(appointmentDTOList, pageable, appointmentPage.getTotalElements());
    }

    @Override
    public Page<AppointmentDTO> getAppointmentsByPageableAndSpec(Specification<Appointment> spec, Pageable pageable) {

        Page<Appointment> appointmentsPage = appointmentJpaRepository.findAll(spec, pageable);
        List<AppointmentDTO> appointmentDTOs = appointmentsPage.stream()
                .map(appointmentMapper::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(appointmentDTOs, pageable, appointmentsPage.getTotalElements());
    }

    @Override
    @Transactional
    public AppointmentDTO updateAppointment(Long id, AppointmentDTO appointmentDTO) {

        appointmentDTO.setId(id);

        Appointment appointment = appointmentJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found with id " + id));

        Optional<Pet> petOptional = petJpaRepository.findById(appointmentDTO.getPet().getId());
        if (petOptional.isPresent()) {
            appointment.setPet(petOptional.get());
            appointment.getPet().setName(appointmentDTO.getPet().getName());
        } else {
            throw new ResourceNotFoundException("Pet not found with id " + appointmentDTO.getPet().getId());
        }

        Optional<Doctor> doctorOptional = doctorJpaRepository.findById(appointmentDTO.getDoctor().getId());
        if (doctorOptional.isPresent()) {
            appointment.setDoctor(doctorOptional.get());
            appointment.getDoctor().setFirstName(appointmentDTO.getDoctor().getFirstName());
            appointment.getDoctor().setLastName(appointmentDTO.getDoctor().getLastName());
        } else {
            throw new ResourceNotFoundException("Doctor not found with id " + appointmentDTO.getDoctor().getId());
        }

        appointment.setDate(appointmentDTO.getDate());
        appointment.setDiagnostic(appointmentDTO.getDiagnostic());
        appointment.setStatus(appointmentDTO.getStatus());

       // TODO: update services

        Appointment updatedAppointment = appointmentJpaRepository.save(appointment);
        return appointmentMapper.toDTO(updatedAppointment);
    }
}
