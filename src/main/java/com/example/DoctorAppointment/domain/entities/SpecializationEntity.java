package com.example.DoctorAppointment.domain.entities;

import com.example.DoctorAppointment.domain.entities.intermediary.DoctorSpecialization;
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
@Table(name = "specializations")
public class SpecializationEntity {
    @Id
    private String name;

    @ManyToOne
    @JoinColumn(name = "doctor_specialization_id")
    private DoctorSpecialization doctorSpecialization;
}
