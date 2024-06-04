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
@Table(name = "doctors")
public class DoctorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer age;

    @OneToOne(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private DoctorSpecialization doctorSpecialization;
}
