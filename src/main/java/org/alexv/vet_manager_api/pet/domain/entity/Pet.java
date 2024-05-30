package org.alexv.vet_manager_api.pet.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.alexv.vet_manager_api.appointment.domain.entity.Appointment;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "pet")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;

    @Column(name = "created_at")
    Instant createdAt;

    @Column(name = "updated_at")
    Instant updatedAt;

    @Version
    @Column(name = "version", nullable = false)
    Long version;

    @PrePersist
    private void onCreate() {
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
