package org.alexv.vet_manager_api.appointment.service.impl;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.vet_manager_api.appointment.domain.dto.NewAppointmentDTO;
import org.alexv.vet_manager_api.appointment.domain.dto.AppointmentDTO;
import org.alexv.vet_manager_api.appointment.domain.dto.AppointmentUpdateDTO;
import org.alexv.vet_manager_api.appointment.domain.dto.AppointmentsDTO;
import org.alexv.vet_manager_api.appointment.domain.entity.Appointment;
import org.alexv.vet_manager_api.appointment.domain.repository.AppointmentJpaRepository;
import org.alexv.vet_manager_api.appointment.mapper.impl.AppointmentMapper;
import org.alexv.vet_manager_api.appointment.service.AppointmentService;
import org.alexv.vet_manager_api.commons.config.PropertiesConfig;
import org.alexv.vet_manager_api.commons.exception.ResourceNotFoundException;
import org.alexv.vet_manager_api.commons.utils.NameUtils;
import org.alexv.vet_manager_api.doctor.domain.entity.Doctor;
import org.alexv.vet_manager_api.doctor.domain.repository.DoctorJpaRepository;
import org.alexv.vet_manager_api.pet.domain.entity.Pet;
import org.alexv.vet_manager_api.pet.domain.repository.PetJpaRepository;
import org.alexv.vet_manager_api.service.domain.dto.NewServiceDTO;
import org.alexv.vet_manager_api.service.domain.entity.Currency;
import org.alexv.vet_manager_api.service.domain.entity.Price;
import org.alexv.vet_manager_api.service.domain.repository.ServiceJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    PropertiesConfig propertiesConfig;
    ServiceJpaRepository serviceJpaRepository;


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
    public AppointmentDTO updateAppointment(Long id, AppointmentUpdateDTO appointmentDTO) {

//        appointmentDTO.setId(id);
//
//        Appointment appointment = appointmentJpaRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Appointment not found with id " + id));
//
//        Pet pet = petJpaRepository.findByName(appointmentDTO.getPetName())
//                .orElseThrow(() -> new ResourceNotFoundException("Pet not found with name " + appointmentDTO.getPet().getName()));
//        appointment.setPet(pet);
//
//        Doctor doctor = doctorJpaRepository.findByFirstNameAndLastName(appointmentDTO.get, appointmentRequestDTO.getDoctorLastName())
//                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with name " + appointmentRequestDTO.getDoctorFirstName() + " " + appointmentRequestDTO.getDoctorLastName()));
//        appointment.setDoctor(doctor);
//
//        appointment.setDate(appointmentRequestDTO.getDate());
//        appointment.setDiagnostic(appointmentRequestDTO.getDiagnostic());
//        appointment.setStatus(appointmentRequestDTO.getStatus());

        // TODO: update services

//        Appointment updatedAppointment = appointmentJpaRepository.save(appointment);
//        return appointmentMapper.toDTO(updatedAppointment);

        return null;
    }

    @Override
    @Transactional
    public AppointmentDTO addAppointment(NewAppointmentDTO newAppointmentDTO) {

        Pet pet;
        Doctor doctor;
        List<org.alexv.vet_manager_api.service.domain.entity.Service> allServices = new ArrayList<>();


        if (newAppointmentDTO.getNewPet() != null && newAppointmentDTO.getNewPet()) {
            String newPetName = (String) newAppointmentDTO.getPet();
            pet = new Pet();
            pet.setName(newPetName);
            pet = petJpaRepository.save(pet);
        } else {
            Long existingPetId = Long.valueOf((Integer) newAppointmentDTO.getPet());
            pet = petJpaRepository.findById(existingPetId)
                    .orElseThrow(() -> new ResourceNotFoundException("Pet not found with id " + existingPetId));
        }

        if (newAppointmentDTO.getNewDoctor() != null && newAppointmentDTO.getNewDoctor()) {
            String[] nameParts = NameUtils.splitFullName((String) newAppointmentDTO.getDoctor());
            String doctorFirstName = nameParts[0];
            String doctorLastName = nameParts[1];
            doctor = new Doctor();
            doctor.setFirstName(doctorFirstName);
            doctor.setLastName(doctorLastName);
            doctor = doctorJpaRepository.save(doctor);
        } else {
            Long existingDoctorId = (Long) newAppointmentDTO.getDoctor();
            doctor = doctorJpaRepository.findById(existingDoctorId)
                    .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id " + existingDoctorId));
        }

        List<Long> services = newAppointmentDTO.getServices();
        List<NewServiceDTO> newServices = newAppointmentDTO.getNewServices();

        Appointment appointment = new Appointment();
        appointment.setPet(pet);
        appointment.setDoctor(doctor);
        appointment.setDate(newAppointmentDTO.getDate());
        appointment.setDiagnostic(newAppointmentDTO.getDiagnostic());
        appointment.setStatus(newAppointmentDTO.getStatus());


        if (services != null && !services.isEmpty()) {

            List<org.alexv.vet_manager_api.service.domain.entity.Service> serviceList = services
                    .stream()
                    .map(serviceId -> serviceJpaRepository.findById(serviceId)
                            .orElseThrow(() -> new ResourceNotFoundException("Service not found with id " + serviceId)))
                    .toList();

            allServices.addAll(serviceList);
        }

        if (newServices != null && !newServices.isEmpty()) {

            List<org.alexv.vet_manager_api.service.domain.entity.Service> savedNewServices = newServices
                    .stream()
                    .map(newServiceDTO -> {
                        Price price = new Price();
                        price.setCurrency(Currency.valueOf(propertiesConfig.getCurrency()));
                        price.setCost(newServiceDTO.getPrice());
                        org.alexv.vet_manager_api.service.domain.entity.Service service = new org.alexv.vet_manager_api.service.domain.entity.Service();
                        service.setName(newServiceDTO.getName());
                        service.setPrice(price);
                        return serviceJpaRepository.save(service);
                    })
                    .toList();

            allServices.addAll(savedNewServices);
        }

        appointment.setServices(allServices);

        Appointment savedAppointment = appointmentJpaRepository.save(appointment);
        return appointmentMapper.toDTO(savedAppointment);
    }


}
