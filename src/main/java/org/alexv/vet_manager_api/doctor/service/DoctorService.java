package org.alexv.vet_manager_api.doctor.service;

import org.alexv.vet_manager_api.appointment.domain.entity.Appointment;
import org.alexv.vet_manager_api.doctor.domain.entity.Doctor;

import java.util.List;

public interface DoctorService {

    List<Doctor> getAllDoctors();
}
