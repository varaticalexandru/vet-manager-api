package org.alexv.vet_manager_api.appointment.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.alexv.vet_manager_api.appointment.domain.repository.AppointmentJpaRepository;
import org.alexv.vet_manager_api.doctor.domain.entity.Doctor;
import org.alexv.vet_manager_api.pet.domain.entity.Pet;
import org.alexv.vet_manager_api.service.domain.entity.Service;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "appointment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    Instant date;

    @Column
    String diagnostic;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    Pet pet;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255) default 'CREATED'")
    AppointmentStatus status;

    @ManyToOne
    Doctor doctor;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "appointment_service",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    List<Service> services;

    @Column(name = "created_at")
    Instant createdAt;

    @Column(name = "updated_at")
    Instant updatedAt;

    @Version
    @Column(name = "version", nullable = false)
    Long version;



    @PrePersist
    private void onCreate() {
//        if (status == null) {
//            status = AppointmentStatus.CREATED;
//        }

        if (date == null) {
            date = Instant.now();
        }

        this.updatedAt = this.createdAt = Instant.now();
    }

    @PreUpdate
    private void onUpdate() {
        this.updatedAt = Instant.now();
    }

    public boolean isTransient() {
        return version == null;
    }

}
