package com.example.DoctorAppointment.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "appointments")
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patients_id")
    private Long id;
    private LocalDateTime date;
    private String patientName;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientEntity patientEntity;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private DoctorEntity doctorEntity;
}
