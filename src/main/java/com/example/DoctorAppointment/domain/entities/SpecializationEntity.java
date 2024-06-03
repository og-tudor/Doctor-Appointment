package com.example.DoctorAppointment.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "specializations")
public class SpecializationEntity {
    @Id
    private Long id;
    private String name;
    @OneToMany(mappedBy = "specializationEntity")
    private Set<DoctorEntity> doctorEntities;
}
