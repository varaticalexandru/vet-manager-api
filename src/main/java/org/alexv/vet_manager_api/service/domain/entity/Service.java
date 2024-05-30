package org.alexv.vet_manager_api.service.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.alexv.vet_manager_api.appointment.domain.entity.Appointment;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "service")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;

    @Embedded
    Price price;

    @ManyToMany(mappedBy = "services")
    List<Appointment> appointments;

    @Column(name = "created_at")
    Instant createdAt;

    @Column(name = "updated_at")
    Instant updatedAt;

    @Version
    @Column(name = "version", nullable = false)
    Long version;

    public boolean isTransient() {
        return version == null;
    }
}
