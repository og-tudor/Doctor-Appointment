package com.example.DoctorAppointment.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "doctors")
public class DoctorEntity {
    @Id
    private Long id;
    private String name;
    private Integer age;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "specialization_id")
    private SpecializationEntity specializationEntity;
}
