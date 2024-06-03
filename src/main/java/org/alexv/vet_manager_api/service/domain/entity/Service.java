package org.alexv.vet_manager_api.service.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.alexv.vet_manager_api.appointment.domain.entity.Appointment;
import org.springframework.beans.factory.annotation.Autowired;

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

    @ManyToMany(mappedBy = "services", fetch = FetchType.LAZY)
    @JsonBackReference
    List<Appointment> appointments;

    @Column(name = "created_at")
    Instant createdAt;

    @Column(name = "updated_at")
    Instant updatedAt;

    @Version
    @Column(name = "version", nullable = false)
    Long version;

    public Service(String name, Price price) {
        this.name = name;
        this.price = price;
    }

    public boolean isTransient() {
        return version == null;
    }
}
