package org.alexv.vet_manager_api.appointment.domain.repository.specification;

import jakarta.persistence.criteria.Join;
import org.alexv.vet_manager_api.appointment.domain.entity.Appointment;
import org.alexv.vet_manager_api.appointment.domain.entity.AppointmentStatus;
import org.alexv.vet_manager_api.service.domain.entity.Service;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;
import java.util.Arrays;

public class AppointmentSpecification {

    public static Specification<Appointment> filterByField(String fieldName, String value) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get(fieldName)), "%" + value.toLowerCase() + "%");
    }

    public static Specification<Appointment> filterByPetName(String petName) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("pet").get("name")), "%" + petName.toLowerCase() + "%");
    }

    public static Specification<Appointment> filterByDoctorFirstName(String firstName) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("doctor").get("firstName")), "%" + firstName.toLowerCase() + "%");
    }

    public static Specification<Appointment> filterByDoctorLastName(String lastName) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("doctor").get("lastName")), "%" + lastName.toLowerCase() + "%");
    }

    public static Specification<Appointment> filterByStatus(String status) {

        try {
            AppointmentStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid status: " + status + ". Must be one of: " + Arrays.toString(AppointmentStatus.values()));
        }

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(criteriaBuilder.upper(root.get("status")), status.toUpperCase());
    }

    public static Specification<Appointment> filterByDate(Instant date) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("date"), date);
    }

    public static Specification<Appointment> filterByDiagnostic(String diagnostic) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("diagnosis")), "%" + diagnostic.toLowerCase() + "%");
    }

    public static Specification<Appointment> filterByTotalCost(Double totalCost) {
        return (root, query, criteriaBuilder) -> {
            Join<Appointment, Service> services = root.join("services");
            query.groupBy(root.get("id"));
            return criteriaBuilder.equal(criteriaBuilder.sum(services.get("price").get("cost")), totalCost);
        };
    }
}
